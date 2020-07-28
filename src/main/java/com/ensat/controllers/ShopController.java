package com.ensat.controllers;

import com.ensat.entities.Product;
import com.ensat.entities.Shop;
import com.ensat.services.ProductService;
import com.ensat.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class ShopController {

    private ShopService shopService;

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping("shop/new")
    public String newShop(Model model){
        model.addAttribute("shop",new Shop());
        return "shopform";
    }
}
