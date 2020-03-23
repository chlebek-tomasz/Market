package com.chlebek.project.dto.product;

import com.chlebek.project.model.product.Category;
import com.chlebek.project.model.util.Image;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProductDto {
    @NotNull
    @Size(max = 70)
    private String name;
    @Size(max = 9000)
    private String description;
    @NotNull
    private String category;
    @NotNull
    @NumberFormat
    private double price;
    private Long userId;
    private List<Image> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
