package com.chlebek.project.repository.product;

import com.chlebek.project.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    Product getById(Long id);

    List<Product> getAllByCategoryId(Long categoryId);
}
