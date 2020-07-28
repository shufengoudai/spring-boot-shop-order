package com.ensat.services;

import com.ensat.entities.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {

    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);

    ArrayList<Product> getProductByProductId(Integer id);

    void alterStockNum(Integer remainNum,Integer id);

    List<Product> getProductsById(List<Integer> ids);

}
