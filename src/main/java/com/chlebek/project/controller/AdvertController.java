package com.chlebek.project.controller;

import com.chlebek.project.dto.form.MessageForm;
import com.chlebek.project.model.product.Category;
import com.chlebek.project.model.product.Product;
import com.chlebek.project.model.util.Message;
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

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/advert")
public class AdvertController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @GetMapping("/add")
    public String getAdvertAddForm(Model model, Model model2) {
        model2.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", new ProductDto());
        return "addadvert";
    }

    @PostMapping("/add")
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
            model.addAttribute("id", product.getId());
            return "redirect:/advert/{id}";
        }
    }

    @GetMapping("/{id}")
    public String getAdvertForm(@PathVariable ("id") Long id, Model model, Model model2){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model2.addAttribute("message", new Message());
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

    @PostMapping("/{id}/sendmessage")
    public String sendMessage(@PathVariable("id") Long id, @ModelAttribute("message")Message message){
        message.setSendDate(new Date());
        message.setSenderId(userService.setUser().getId());
        message.setReceiverId(productService.getProductById(id).getUser().getId());
        messageService.save(message);
        return "redirect:/advert/{id}";
    }

}