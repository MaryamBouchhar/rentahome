package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Models.Image;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Repositories.ImageRepository;
import com.fpt.rentahome.Repositories.PropertyRepository;
import com.fpt.rentahome.Services.ImageService;
import com.fpt.rentahome.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/uploads")
public class ImageController {
    @Autowired
    PropertyController propertyController;
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    PropertyService propertyService;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageService imageService;

    @GetMapping("/images/{imageName:.+}")
    public Resource getImage(@PathVariable String imageName) throws Exception {
        Path imagePath = Paths.get("src/main/webapp/uploads/images/" + imageName);
        return new UrlResource(imagePath.toUri());
    }

    //get all images of a property
    @GetMapping("/properties/{id}")
    public Resource getImages(@PathVariable int id) throws Exception {
        //get the image (url attribute) of the property
        Property property = propertyRepository.findById(id).get();
        List<Image> images = imageRepository.findImageByProperty(property);
        String url = images.get(0).getUrl();
        Path imagePath = Paths.get("src/main/webapp/uploads/images/" + url);
        return new UrlResource(imagePath.toUri());
    }

    //get only the first image of a property and check if the property has images,
    //if not, return the default image
    @GetMapping(value = "/properties/{id}/first", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getFirstImage(@PathVariable int id) throws IOException, IOException {
        // get the image (url attribute) of the property
        Property property = propertyRepository.findById(id).orElse(null);
        if (property == null) {
            return ResponseEntity.notFound().build();
        }

        List<Image> images = imageRepository.findImageByProperty(property);
        if (images.isEmpty()) {
            // return a default image if the property has no images
            ClassPathResource defaultImage = new ClassPathResource("static/default.jpg");
            byte[] bytes = StreamUtils.copyToByteArray(defaultImage.getInputStream());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        }

        // read the image file from the file system and return it as a byte array
        File file = new File("src/main/webapp/uploads/images/" + images.get(0).getUrl());
        byte[] bytes = Files.readAllBytes(file.toPath());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
}
