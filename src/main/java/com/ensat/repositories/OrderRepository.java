package com.ensat.repositories;

import com.ensat.entities.Orders;
import com.ensat.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository extends CrudRepository<Orders, Integer>, JpaRepository<Orders,Integer> {
    @Transactional()
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into orders(product_id,order_num,shop_id) values(?1,?2,?3)",nativeQuery = true)
    void putOrder(Integer productId,Integer orderNum,Integer id);

    @Query(value = "select order_num from orders where product_id = ?1",nativeQuery = true)
    Integer getOrderNum(Integer productId);

    @Query(value = "select shop_id from orders",nativeQuery = true)
    List<Integer> getShopIds();
}
