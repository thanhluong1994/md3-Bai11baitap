package com.Sanpham.service;

import com.Sanpham.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> finAll();

    void save(Product product);

    Product findById(int id);

    void update(int id, Product customer);

    void remove(int id);

}
