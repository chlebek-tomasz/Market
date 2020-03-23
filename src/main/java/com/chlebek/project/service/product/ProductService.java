package com.chlebek.project.service.product;

import com.chlebek.project.model.product.Product;
import com.chlebek.project.dto.product.ProductDto;

import java.util.List;

public interface ProductService {
    Product getProductByName(String name);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    List<Product> getAllProductsFromCategory(Long categoryId);

    Product addProduct(ProductDto product);

    void updateProduct(Product product);

    void delete(Product product);
}