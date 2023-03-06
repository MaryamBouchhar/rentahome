package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/manage-property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //FETCH ALL Clients
    @GetMapping("/properties")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

}
