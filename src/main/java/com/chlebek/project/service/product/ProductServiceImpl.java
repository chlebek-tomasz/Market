package com.chlebek.project.service.product;

import com.chlebek.project.model.product.Product;
import com.chlebek.project.repository.product.CategoryRepository;
import com.chlebek.project.repository.product.ProductRepository;
import com.chlebek.project.service.UserService;
import com.chlebek.project.dto.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsFromCategory(Long categoryId) {
        return productRepository.getAllByCategoryId(categoryId);
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(categoryRepository.findByName(productDto.getCategory()));
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImages(productDto.getImages());
        product.setUser(userService.setUser());
        product.setAddedDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
