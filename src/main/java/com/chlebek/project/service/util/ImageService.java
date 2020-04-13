package com.chlebek.project.service.util;

import com.chlebek.project.model.util.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImage(Image image, MultipartFile file) throws Exception;

    void save(Image image);
}
