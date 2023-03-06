package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Comment;
import com.fpt.rentahome.Models.Property;

import com.fpt.rentahome.Repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    //get all properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    //get recent properties
    public List<Property> getRecentProperties() {
        List<Property> properties = propertyRepository.findAll();
        List<Property> recentProperties = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            recentProperties.add(properties.get(i));
        }
        return recentProperties;
    }
}
