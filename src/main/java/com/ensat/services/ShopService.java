package com.ensat.services;

import com.ensat.entities.Product;
import com.ensat.entities.Shop;

import java.util.ArrayList;

public interface ShopService {
    Shop getShopById(Integer id);

    ArrayList<Shop> getShop();

    Integer getShopIdById(Integer id);
}
