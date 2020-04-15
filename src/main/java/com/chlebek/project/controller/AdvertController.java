package com.chlebek.project.controller;

import com.chlebek.project.model.product.Product;
import com.chlebek.project.model.util.Image;
import com.chlebek.project.model.util.Message;
import com.chlebek.project.repository.util.ImageRepository;
import com.chlebek.project.service.UserService;
import com.chlebek.project.service.product.CategoryService;
import com.chlebek.project.service.product.ProductService;
import com.chlebek.project.dto.product.ProductDto;
import com.chlebek.project.service.util.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/advert")
public class AdvertController {
    public static final String uploadingDir = System.getProperty("user.dir") + "/uploadingDir/";
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/add")
    public String getAdvertAddForm(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", new ProductDto());
        return "addadvert";
    }

    @PostMapping("/add")
    public String addAdvert(@ModelAttribute("product") ProductDto productDto, @RequestParam(value = "images", required = false) MultipartFile[] file, BindingResult result, Model model, HttpServletRequest request) throws Exception {
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
        } else if (result.hasErrors()) {
            result.rejectValue("images", "error.product", "Problem with image");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "addadvert";
        } else {
            Product product = productService.addProduct(productDto);
            model.addAttribute("id", product.getId());
            return "redirect:/advert/{id}";
        }
    }

    @GetMapping("/{id}")
    public String getAdvertForm(@PathVariable ("id") Long id, Model model, Model model2){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("message", new Message());
        return "productForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdvert(@PathVariable ("id") Long id){
        if(productService.getProductById(id).getUser().getId().equals(userService.setUser().getId())) {
            productService.delete(id);
            return "redirect:/profile/myproducts";
        } else return "dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editAdvertForm(@PathVariable("id") Long id, Model model, Model model2) {
        Product product = productService.getProductById(id);
        ProductDto productDto = productService.setProductDto(product);
        if (product.getUser().getId().equals(userService.setUser().getId())) {
            model2.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("product", productDto);
            return "editadvert";
        } else {
            return "dashboard";
        }
    }

    @PostMapping("/edit/{id}")
    public String editAdvert(@PathVariable("id") Long id, @ModelAttribute("product") ProductDto productDto, Model model){
        Product product = productService.setProduct(productDto);
        product.setId(id);
        product.setAddedDate(productService.getProductById(product.getId()).getAddedDate());
        productService.updateProduct(product);
        return "redirect:/advert/{id}";
    }

}