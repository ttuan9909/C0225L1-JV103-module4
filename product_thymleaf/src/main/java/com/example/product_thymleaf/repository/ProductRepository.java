package com.example.product_thymleaf.repository;

import com.example.product_thymleaf.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        TypedQuery<Product> query = entityManager.createQuery("from Product", Product.class);
        productList = query.getResultList();
        return productList;
    }

    @Override
    public Product findById(int id) {
        Product product = entityManager.find(Product.class,id);
        return product;
    }

    @Transactional
    @Override
    public boolean add(Product product) {
        try{
            entityManager.persist(product);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean update(Product product) {
        try {
            entityManager.merge(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
             try {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
            entityManager.flush();
            return true;
        }
        return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }


    @Override
    @Transactional(readOnly = true)
    public Product findByName(String name) {
        // Dùng JPQL thay vì entityManager.find(...)
        TypedQuery<Product> q = entityManager.createQuery(
                "select p from Product p where lower(p.name) = :name", Product.class);
        q.setParameter("name", name == null ? null : name.toLowerCase());
        return q.getResultStream().findFirst().orElse(null);
    }
}
