package com.alwaha.cafe.dao;

import com.alwaha.cafe.models.Product;
import com.alwaha.cafe.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    List<ProductWrapper> getAllProducts();

    @Modifying
    @Transactional
    Integer updateStatus(@Param("status") String status,@Param("id") Integer id);

    List<ProductWrapper> getByCategory(@Param("id") Integer id);

    ProductWrapper getProductById(Integer id);
}
