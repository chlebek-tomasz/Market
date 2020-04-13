package com.chlebek.project.service.util;

import com.chlebek.project.model.util.Image;
import com.chlebek.project.repository.util.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public void saveImage(Image image, MultipartFile file) throws Exception {
        Path currentPath = Paths.get("");
        Path absolutePath = currentPath.toAbsolutePath();
        image.setPath(absolutePath + "/src/main/resources/static/images/");
        byte[] bytes = file.getBytes();
        Path path = Paths.get(image.getPath() + file.getOriginalFilename());
        Files.write(path, bytes);
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }
}
