package com.chlebek.project.repository.product;

import com.chlebek.project.model.product.Category;
import com.chlebek.project.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    Product getById(Long id);

    List<Product> getAllByCategoryId(Long categoryId);

    List<Product> getAllByUserId(Long userId);

    void deleteById(Long id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE '%:text%'")
    List<Product> getProductBySearchingByText(String text);

    @Query("SELECT p FROM Product p WHERE p.name LIKE '%:text%' AND p.category=:category")
    List<Product> getProductBySearchingByTextAndCategory(String text, Category category);
}
