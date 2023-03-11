package com.fpt.rentahome.Services;

import com.fpt.rentahome.Controllers.PropertyController;
import com.fpt.rentahome.Models.*;

import com.fpt.rentahome.Repositories.ImageRepository;
import com.fpt.rentahome.Repositories.PropertyRepository;
//import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

//import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletContext;

@Service
public class PropertyService implements ServletContextAware {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;
    private ServletContext servletContext;

    //get all properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    //get recent properties
    public List<Property> getRecentProperties() {
        List<Property> properties = propertyRepository.findAll();
        List<Property> recentProperties = new ArrayList<>();

        if (properties.size() > 5) {
            for (int i = 0; i < 5; i++) {
                recentProperties.add(properties.get(i));
            }
        } else {
            for (int i = 0; i < properties.size(); i++) {
                recentProperties.add(properties.get(i));
            }
        }
        return recentProperties;
    }

    public void createProperty(Property property, List<MultipartFile> images) throws Exception {
        //create the location
        Location location = new Location();
        location.setAddress(property.getLocation().getAddress());
        location.setCity(property.getLocation().getCity());
        location.setLongitude(property.getLocation().getLongitude());
        location.setLatitude(property.getLocation().getLatitude());

        //save the property
        property.setLocation(location);
        propertyRepository.save(property);

        //save the images
        int count = 0;

        for (MultipartFile image : images) {
            System.out.println("image name: " + image.getOriginalFilename());

            String name = saveImage(image);

            Image img = new Image();
            img.setUrl(name);
            img.setProperty(property);
            //check if theres an already existing image with same name
            if (imageService.checkImageExist(name) == null) {
                imageRepository.save(img);
            }
            count++;
        }
        System.out.println("Total images: " + count);
    }

    public Optional<Property> getPropertyById(int id) {
        return propertyRepository.findById(id);
    }

    public boolean updateProperty(int id, Property updatedProperty) {

        // Check if an admin with the given ID exists
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        if (!propertyOptional.isPresent()) {
            return false;
        }

        // Update the admin object
        Property property = propertyOptional.get();


        // Save the updated admin to the database
        propertyRepository.save(property);

        return true;
    }

    //get the latest property id
    public int getLatestPropertyId() {
        List<Property> properties = propertyRepository.findAll();
        return properties.get(properties.size() - 1).getId();
    }

    //save image
    public String saveImage(MultipartFile image) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
            String name = formatter.format(new Date()) + image.getOriginalFilename();
            byte[] bytes = image.getBytes();
            Path path = Paths.get(this.servletContext.getRealPath("/uploads/images/" + name));
            Files.write(path, bytes);
            return name;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
