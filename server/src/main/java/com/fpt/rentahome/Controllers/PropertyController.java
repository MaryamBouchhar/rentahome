package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Comment;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Repositories.CommentRepository;
import com.fpt.rentahome.Repositories.PropertyRepository;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
