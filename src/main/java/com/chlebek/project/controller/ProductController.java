package com.chlebek.project.controller;

import com.chlebek.project.model.product.Product;
import com.chlebek.project.service.product.CategoryService;
import com.chlebek.project.service.product.ProductService;
import com.chlebek.project.dto.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/advert/add")
    public String getAdvertAddForm(Model model, Model model2) {
        model2.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", new ProductDto());
        return "addadvert";
    }

    @PostMapping("/advert/add")
    public String addAdvert(@ModelAttribute("product") ProductDto productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            result.rejectValue("name", "error.product", "Name too short");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "addadvert";
        } else if (result.hasErrors()) {
            result.rejectValue("description", "error.product", "Description too long");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "addadvert";
        } else if (result.hasErrors()) {
            result.rejectValue("category", "error.product", "Category cannot be empty");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "addadvert";
        } else if (result.hasErrors()) {
            result.rejectValue("price", "error.product", "Price cannot be empty and must be number format");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "addadvert";
        } else {
            Product product = productService.addProduct(productDto);
            model.addAttribute("product", product);
            return "/product/{product.id}";
        }
    }

    @GetMapping("/product/{product.id}")
    public String getProductForm(@PathVariable ("product.id") Long id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productForm";
    }
}