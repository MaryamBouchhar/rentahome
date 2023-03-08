package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Comment;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Repositories.CommentRepository;
import com.fpt.rentahome.Repositories.PropertyRepository;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/manage-properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CommentRepository commentRepository;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //fetch all properties
    @GetMapping("/properties")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }


    //get property
    @GetMapping("/properties/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable("id") int id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        if (property == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Property getedproperty= property.get();
        return new ResponseEntity<>(getedproperty, HttpStatus.OK);
    }


    //add a property
    @PostMapping("/add-property")
    public ResponseEntity<ApiResponse> createProperty(@RequestBody Property property) {
        propertyService.createProperty(property);
        return new ResponseEntity<>(new ApiResponse(true, "created the property"), HttpStatus.CREATED);
    }



    //update property
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProperty(@PathVariable("id") int id, @RequestBody Property property) {

        ApiResponse apiResponse = new ApiResponse();
        HttpStatus status;

        // Call the service method to update the admin with the given ID
        boolean success = propertyService.updateProperty(id, property);

        if (success) {
            apiResponse.setMessage("Property updated successfully");
            status = HttpStatus.OK;
        } else {
            apiResponse.setMessage("Property not found");
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(apiResponse, status);
    }

    //fetch recent properties
    @GetMapping("/recent-properties")
    public List<Property> getFeaturedProperties() {
        return propertyService.getRecentProperties();
    }

    //add a comment
    @GetMapping("/{propertyId}/add-comment")
    public Comment addComment(@PathVariable Long propertyId, @RequestBody Comment comment) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property == null) {
            throw new RuntimeException("Property not found");
        }
        comment.setProperty(property);
        return commentRepository.save(comment);
    }
}
