package com.chlebek.project.config;

import com.chlebek.project.service.UserService;
import com.chlebek.project.service.UserServiceImpl;
import com.chlebek.project.service.product.CategoryService;
import com.chlebek.project.service.product.CategoryServiceImpl;
import com.chlebek.project.service.product.ProductService;
import com.chlebek.project.service.product.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean(name = "categoryService")
    public CategoryService categoryService(){
        return new CategoryServiceImpl();
    }

    @Bean(name = "productService")
    public ProductService productService(){
        return new ProductServiceImpl();
    }

    @Bean(name = "userService")
    public UserService userService() { return new UserServiceImpl(); }
}
