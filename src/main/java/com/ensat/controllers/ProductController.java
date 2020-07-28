package com.ensat.controllers;

import com.ensat.entities.Orders;
import com.ensat.entities.Product;
import com.ensat.entities.Shop;
import com.ensat.services.OrderService;
import com.ensat.services.ProductService;
import com.ensat.services.ShopService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.buf.UDecoder;
import org.apache.tomcat.util.buf.UEncoder;
import org.graalvm.compiler.hotspot.replacements.IdentityHashCodeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.runtime.Threads;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Product controller.
 */
@Controller
public class ProductController {
//    private static Integer shopId;
//    private static HashMap<Product,Integer> map = new HashMap<>();

    private ProductService productService;
    private ShopService shopService;
    private OrderService orderService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * List all products.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("Returning products:");
        return "products";
    }

    /**
     * View a specific product by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    // Afficher le formulaire de modification du Product
    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }

    /**
     * New product.
     *
     * @param model
     * @return
     */
    @RequestMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }

    /**
     * Save product to database.
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/product/" + product.getId();
    }

    /**
     * Delete product by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    //第一个页面跳转到第二个页面
    //1：
    @RequestMapping(value = "/productPC",method = RequestMethod.POST)
    public String computerOrder1(@RequestParam("number")Integer productId){
//        shopId = productId;
        return "redirect:/productPC/" + productId;
    }

    //2:
    @RequestMapping("productPC/{productId}")
    public String computerOrder(@PathVariable Integer productId, Model model, HttpServletRequest request) {
        ArrayList<Product> products = productService.getProductByProductId(productId);
        ArrayList<Product> products2 = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().trim().substring(0,2).equals("PC")){
                products2.add(product);
            }
        }
        String shopName = shopService.getShopById(productId).getShopName();
        model.addAttribute("PCs",products2);
        model.addAttribute("shopName",shopName);
        model.addAttribute("shopId",productId);
        return "computerorder";
    }

    //第二个页面值存储
    @RequestMapping(value = "/productPCSubmit",method = RequestMethod.POST)
    @ResponseBody
    public String computerOrderSubmit(@RequestBody String str){
//
        if (str != null){
            ObjectMapper objectMapper = new ObjectMapper();
//            str = URLDecoder.decode(str);
            str = UDecoder.URLDecode(str);
            str = str.substring(5);
            System.out.println(str);
            try {
                JsonNode jsonNode = objectMapper.readTree(str);
                int size = jsonNode.size();
                for (int i = 0; i < size; i++) {
                    int id = jsonNode.get(i).get("id").asInt();
                    int orderNum = jsonNode.get(i).get("orderNum").asInt();
                    Integer shopId = shopService.getShopIdById(id);
                    orderService.putOrder(id,orderNum,shopId);
//                    Product product = productService.getProductById(id);
//                    System.out.println(product);
//                    map.put(product,orderNum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//
        return "success";
    }

    @RequestMapping("/transfer")
    public String transfer1(Model model){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer shopId = orderService.getShopId();
        ArrayList<Product> products = productService.getProductByProductId(shopId);
        ArrayList<Product> products2 = new ArrayList<>();
        for (Product pro : products) {
            String proName = pro.getName();
            int length = proName.length();
            if (proName.trim().substring(0,1).equals("V")){
                products2.add(pro);
//                System.out.println(proName);
            }
        }
        String shopName = shopService.getShopById(shopId).getShopName();
        model.addAttribute("rams",products2);
        model.addAttribute("shopId",shopId);
        model.addAttribute("shopName",shopName);
        return "ramorder";
    }

    //对第三个页面提交的数据进行处理
    @RequestMapping("/productPCSubmit2")
    @ResponseBody
    public String ramOrderSubmit(@RequestBody String stri,Model model){
        if (stri != null){
            ObjectMapper objectMapper = new ObjectMapper();
//            str = URLDecoder.decode(str);
            stri = UDecoder.URLDecode(stri);
            stri = stri.substring(5);
            System.out.println(stri);
            try {
                JsonNode jsonNode = objectMapper.readTree(stri);
                int size = jsonNode.size();
                for (int i = 0; i < size; i++) {
                    int id = jsonNode.get(i).get("id").asInt();
                    int orderNum = jsonNode.get(i).get("orderNum").asInt();
                    Integer shopId = shopService.getShopIdById(id);
                    orderService.putOrder(id,orderNum,shopId);
//                    Product product = productService.getProductById(id);
//                    map.put(product,orderNum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println();
        return "success";
    }

    //跳转
    @RequestMapping("/transfer2")
    public String transfer2(Model model){
        int sum = 0;
//        if (map != null){
//            for (Map.Entry<Product, Integer> entry : map.entrySet()) {
//                sum += entry.getKey().getPrice() * entry.getValue();
//            }
//        }else {
//            System.out.println("map还是为空");
//        }
        List<Orders> orders = orderService.findAllOrder();
        for (Orders order : orders) {
            sum += productService.getProductById(order.getProductId()).getPrice() * order.getOrderNum();
        }
        List<Integer> ids = orderService.getProductIdFromAll();
        Map<Product,Integer> map = new HashMap<Product,Integer>();
        for (Integer id : ids) {
            Product product = productService.getProductById(id);
            Integer orderNum = orderService.getOrderNum(product.getId());
            map.put(product,orderNum);
        }
        model.addAttribute("maps",map);
        model.addAttribute("sum",sum);
        return "lastorder";
    }

    //减库存
    @RequestMapping("/decrease")
//    @ResponseBody
    public String decrease(){
        List<Integer> ids = orderService.getProductIdFromAll();
        Map<Product,Integer> map = new HashMap<Product,Integer>();
        for (Integer id : ids) {
            Product product = productService.getProductById(id);
            Integer orderNum = orderService.getOrderNum(product.getId());
            map.put(product,orderNum);
        }
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            Integer id = entry.getKey().getId();
            Integer stockNum = entry.getKey().getStockNum();
            Integer orderNum = entry.getValue();
            Integer remainNum = stockNum - orderNum;
            productService.alterStockNum(remainNum,id);
        }
        orderService.deleteAll();
//        str = UDecoder.URLDecode(str);
//        System.out.println(str);
        return "forward:/comeback2";
    }

}
