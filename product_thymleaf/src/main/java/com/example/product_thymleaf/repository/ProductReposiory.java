package com.example.product_thymleaf.repository;

import com.example.product_thymleaf.entity.Product;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductReposiory implements IProductRepository {
    private static final List<Product> products = new ArrayList<>();
    private static int AUTO_ID = 1; // giả lập auto increment

    static {
        products.add(new Product(AUTO_ID++, "Laptop Dell", 1500, "Laptop văn phòng", "Dell"));
        products.add(new Product(AUTO_ID++, "iPhone 15", 1200, "Điện thoại mới nhất", "Apple"));
        products.add(new Product(AUTO_ID++, "Sony Headphone", 200, "Tai nghe chống ồn", "Sony"));
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);    }

    @Override
    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean add(Product product) {
        product.setId(AUTO_ID++);
        return products.add(product);
    }

    @Override
    public boolean update(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return products.removeIf(p -> p.getId() == id);
    }

    @Override
    public Product findByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
