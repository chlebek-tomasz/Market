package com.chlebek.project.repository.util;

import com.chlebek.project.model.util.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
