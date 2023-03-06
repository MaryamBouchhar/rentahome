package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Repositories.ClientRepository;
import com.fpt.rentahome.Repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;


    public List<Property> getAllProperties() {
        return propertyRepository.findAll();

    }
}
