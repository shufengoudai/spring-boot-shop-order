package com.ensat.repositories;

import com.ensat.entities.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepository extends CrudRepository<Shop, Integer> {

    @Query(value = "select product_id from product where id = ?",nativeQuery = true)
    Integer getShopIdById(Integer id);
}
