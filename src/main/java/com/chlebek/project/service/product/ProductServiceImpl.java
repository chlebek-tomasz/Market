package com.chlebek.project.service.product;

import com.chlebek.project.model.product.Product;
import com.chlebek.project.model.util.Image;
import com.chlebek.project.repository.product.CategoryRepository;
import com.chlebek.project.repository.product.ProductRepository;
import com.chlebek.project.service.UserService;
import com.chlebek.project.dto.product.ProductDto;
import com.chlebek.project.service.util.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageService imageService;

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsFromCategory(Long categoryId) {
        return productRepository.getAllByCategoryId(categoryId);
    }

    @Override
    public List<Product> getAllUsersProducts(Long userId) {
        return productRepository.getAllByUserId(userId);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product addProduct(ProductDto productDto) throws Exception {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(categoryRepository.findByName(productDto.getCategory()));
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        for(MultipartFile file : productDto.getImages()){
            Image image = new Image(file.getOriginalFilename());
            image.setPath("/images/");
            product.getImages().add(image);
            imageService.saveImage(image, file);
            imageService.save(image);
        }
        product.setUser(userService.setUser());
        product.setAddedDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public ProductDto setProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setPrice(product.getPrice());
        productDto.setUserId(product.getUser().getId());
        return productDto;
    }

    @Override
    public Product setProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setUser(userService.setUser());
        product.setPrice(productDto.getPrice());
        for(MultipartFile file : productDto.getImages()) {
            Image image = new Image(file.getOriginalFilename());
            product.getImages().add(image);
        }
        product.setCategory(categoryRepository.findByName(productDto.getCategory()));
        return product;
    }

    private boolean checkIfProductIdExistDb(Long id){
        if(productRepository.existsById(id)){
            return true;
        } else return false;
    }
}
