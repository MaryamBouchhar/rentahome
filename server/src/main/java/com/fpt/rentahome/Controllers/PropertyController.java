package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
}
