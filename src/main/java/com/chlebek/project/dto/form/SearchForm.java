package com.chlebek.project.dto.form;

import com.chlebek.project.model.product.Category;

public class SearchForm {
    private String text;
    private Category category;

    public SearchForm(String text, Category category){
        this.text = text;
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
