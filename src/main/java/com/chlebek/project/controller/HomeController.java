package com.chlebek.project.controller;

import com.chlebek.project.service.product.CategoryService;
import com.chlebek.project.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/"})
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "index";
    }

    @GetMapping("/category/{id}")
    public String showProductFromCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("products", productService.getAllProductsFromCategory(id));
        return "category";
    }
}
