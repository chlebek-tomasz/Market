package com.chlebek.project.service.product;

import com.chlebek.project.model.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category findByName(String name);
}
