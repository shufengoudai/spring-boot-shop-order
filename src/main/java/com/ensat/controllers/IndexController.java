package com.ensat.controllers;

import com.ensat.entities.Shop;
import com.ensat.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Homepage controller.
 */
@Controller
public class IndexController {
    private ShopService shopService;

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping("/")
    String index(Model model) {
        ArrayList<Shop> shops = shopService.getShop();
        model.addAttribute("shops",shops);
        model.addAttribute("message"," ");
        return "index";
    }

    @RequestMapping("/comeback1")
    String comeback1(Model model){
        ArrayList<Shop> shops = shopService.getShop();
        model.addAttribute("shops",shops);
        String message = "欢迎惠顾";
        model.addAttribute("message",message);
        return "index";
    }

    @RequestMapping("/comeback2")
    String comeback2(Model model){
        ArrayList<Shop> shops = shopService.getShop();
        model.addAttribute("shops",shops);
        String message = "谢谢光临";
        model.addAttribute("message",message);
        return "index";
    }

}
