package com.ensat.services;

import com.ensat.entities.Product;
import com.ensat.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Product service implement.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.delete(id);
    }

    @Override
    public ArrayList<Product> getProductByProductId(Integer id) {
        return productRepository.getProductByProductId(id);
    }

    @Override
    public void alterStockNum(Integer remainNum,Integer id) {
        productRepository.alterStockNum(remainNum,id);
    }

    @Override
    public List<Product> getProductsById(List<Integer> ids) {
        ArrayList<Product> products = new ArrayList<>();
        for (Integer id : ids) {
            Product product = productRepository.findOne(id);
            products.add(product);
        }
        return products;
    }

}