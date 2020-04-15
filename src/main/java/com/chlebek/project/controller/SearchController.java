package com.chlebek.project.controller;

import com.chlebek.project.dto.form.SearchForm;
import com.chlebek.project.model.product.Category;
import com.chlebek.project.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @PostMapping("/search")
    public String search(@ModelAttribute("searchForm") SearchForm searchForm, Model model){
        if(searchForm.getCategory().getId()==0 || searchForm.getCategory().getId()==null){
            model.addAttribute("products", productService.getProductsBySearchingByText(searchForm.getText()));
        } else {
            model.addAttribute("products", productService.getProductsBySearchingByTextAndCategory(searchForm.getText(), searchForm.getCategory()));
        }
        return "search";
    }
}
