package com.chlebek.project.servlet;

import com.chlebek.project.model.product.Category;
import com.chlebek.project.repository.product.CategoryRepository;
import com.chlebek.project.service.product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    @Autowired
    private CategoryService categoryService;
    public void doGet(HttpRequest request, HttpServletResponse response){
        try {
            ServletContext context = getServletContext();
            for(Category category : categoryService.getAllCategories()){
                context.setAttribute("category", category.getName());
                System.out.println(category.getName());
            }
        } catch (Exception e){
            e.getMessage();
        }
    }
}
