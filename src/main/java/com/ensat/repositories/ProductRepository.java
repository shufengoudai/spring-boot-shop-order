package com.ensat.repositories;

import com.ensat.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product, Integer>,JpaRepository<Product,Integer>{
    @Query(value = "select * from product where product_id = :id",nativeQuery = true)
    ArrayList<Product> getProductByProductId(@Param("id") Integer id);


    @Transactional()
    @Modifying(clearAutomatically = true)
    @Query(value = "update product set stock_num =?1 where id = ?2",nativeQuery = true)
    void alterStockNum(Integer remainNum,Integer id);

}
