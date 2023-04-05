package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Image;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void deleteImagesOfProperty(Property property) {
        imageRepository.deleteAllByProperty(property);
    }

    public Image checkImageExist(String name) {
        return imageRepository.findByUrl(name);
    }
}
