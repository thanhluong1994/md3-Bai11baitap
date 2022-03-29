package com.Sanpham.service;

import com.Sanpham.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceLmPl implements ProductService {
private static Map<Integer, Product> products;
static {
    products=new HashMap<>();
    products.put(1,new Product(1,"Dien thoai",3000000,40));
    products.put(2,new Product(2,"May tinh",20000000,60));
    products.put(3,new Product(3,"Tai nghe",200000,80));
    products.put(4,new Product(4,"Tivi",5000000,100));
}
@Override
    public List<Product> finAll(){
    return new ArrayList<>(products.values());
}
    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

}

