package com.ensat.services;

import com.ensat.entities.Product;
import com.ensat.entities.Shop;
import com.ensat.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShopServiceImpl implements ShopService{

    private ShopRepository shopRepository;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop getShopById(Integer id) {
        return shopRepository.findOne(id);
    }

    @Override
    public ArrayList<Shop> getShop() {
        return (ArrayList<Shop>) shopRepository.findAll();
    }

    @Override
    public Integer getShopIdById(Integer id) {
        return shopRepository.getShopIdById(id);
    }
}
