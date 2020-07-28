package com.ensat.services;

import com.ensat.entities.Orders;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    void putOrder(Integer id,Integer orderNum,Integer shopId);

    List<Orders> findAllOrder();

    List<Integer> getProductIdFromAll();

    List<Integer> getOrderNums();

    Integer getOrderNum(Integer productId);

    Integer getShopId();

    void deleteAll();
}
