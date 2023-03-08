package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Models.Comment;
import com.fpt.rentahome.Models.Property;

import com.fpt.rentahome.Repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void createProperty(Property property) {
        propertyRepository.save(property);
    }

    public Optional<Property> getPropertyById(int id) {
        return propertyRepository.findById(id);
    }

    public boolean updateProperty(int id, Property updatedProperty) {

            // Check if an admin with the given ID exists
            Optional<Property> propertyOptional = propertyRepository.findById( id);
            if (!propertyOptional.isPresent()) {
                return false;
            }

            // Update the admin object
             Property  property = propertyOptional.get();



            // Save the updated admin to the database
            propertyRepository.save(property);

            return true;



    }
}
