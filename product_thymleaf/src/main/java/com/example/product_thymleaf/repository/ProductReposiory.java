package com.example.product_thymleaf.repository;

import com.example.product_thymleaf.entity.Product;
import com.example.product_thymleaf.utils.ConnectionUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductReposiory implements IProductRepository {
    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        Session session = ConnectionUtil.sessionFactory.openSession();
        TypedQuery<Product> query = session.createQuery("from Product");
        productList = query.getResultList();
        session.close();
        return productList;
    }

    @Override
    public Product findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Product product = session.find(Product.class,id);
        session.close();
        return product;
    }

    @Override
    public boolean add(Product product) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            session.persist(product);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Product product) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.merge(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // kiểm tra nhanh có bản ghi không
                Product probe = session.get(Product.class, id);
                System.out.println("[DELETE] probe = " + probe);

                int affected = session.createMutationQuery(
                                "delete from Product p where p.id = :id")
                        .setParameter("id", id)
                        .executeUpdate();

                tx.commit();
                System.out.println("[DELETE] affected rows = " + affected);
                return affected > 0;
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public Product findByName(String name) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Product p WHERE lower(p.name) = :name", Product.class)
                    .setParameter("name", name.toLowerCase())
                    .uniqueResult();
        }
    }
}
