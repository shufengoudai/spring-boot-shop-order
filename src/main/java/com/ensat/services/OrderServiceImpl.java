package com.ensat.services;

import com.ensat.entities.Orders;
import com.ensat.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void putOrder(Integer id,Integer orderNum,Integer shopId) {
        orderRepository.putOrder(id,orderNum,shopId);
    }

    @Override
    public List<Orders> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Integer> getProductIdFromAll() {
        ArrayList<Orders> orders = (ArrayList<Orders>) orderRepository.findAll();
        List<Integer> ids = new ArrayList<Integer>();
        for (Orders order : orders) {
            ids.add(order.getProductId());
        }
        return ids;
    }

    @Override
    public List<Integer> getOrderNums() {
        ArrayList<Integer> orderNums = new ArrayList<>();
        List<Orders> orders = orderRepository.findAll();
        for (Orders order : orders) {
            orderNums.add(order.getOrderNum());
        }
        return orderNums;
    }

    @Override
    public Integer getOrderNum(Integer productId) {
        Integer orderNum = orderRepository.getOrderNum(productId);
        return orderNum;
    }

    @Override
    public Integer getShopId() {
        Integer shopId = orderRepository.getShopIds().get(0);
        return shopId;
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }


}
