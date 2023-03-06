package com.fpt.rentahome.Services;

import com.fpt.rentahome.Repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
}
