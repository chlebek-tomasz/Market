package com.chlebek.project.service.product;

import com.chlebek.project.model.product.Category;
import com.chlebek.project.model.product.Product;
import com.chlebek.project.model.util.Image;
import com.chlebek.project.repository.product.CategoryRepository;
import com.chlebek.project.repository.product.ProductRepository;
import com.chlebek.project.service.UserService;
import com.chlebek.project.dto.product.ProductDto;
import com.chlebek.project.service.util.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "chlebekcloudserver",
            "api_key", "475119931464237",
            "api_secret", "-qZbtHRalNkDz5sYV_SHdIMR9pI" ));

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
        if(!productDto.getImages().isEmpty()) {
            for (MultipartFile file : productDto.getImages()) {
                Image image = new Image();
                if(!file.isEmpty()) {
                    Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                    String fileName = (String) uploadResult.get("public_id");
                    String fileUrl = (String) uploadResult.get("url");
                    image.setName(fileName);
                    image.setPath(fileUrl);
                }
                product.getImages().add(image);
                imageService.save(image);
            }
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

    @Override
    public List<Product> getProductsBySearchingByText(String text) {
        return productRepository.getProductBySearchingByText(text);
    }

    @Override
    public List<Product> getProductsBySearchingByTextAndCategory(String text, Category category) {
        return productRepository.getProductBySearchingByTextAndCategory(text, category);
    }

    private boolean checkIfProductIdExistDb(Long id){
        if(productRepository.existsById(id)){
            return true;
        } else return false;
    }
}
