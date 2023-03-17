package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.*;
import com.fpt.rentahome.Repositories.ClientRepository;
import com.fpt.rentahome.Repositories.CommentRepository;
import com.fpt.rentahome.Repositories.PropertyRepository;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Services.PropertyService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/manage-properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

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
    @CrossOrigin(origins = "*")
    @GetMapping("/properties/{id}")
    /*public ResponseEntity<Property> getPropertyById(@PathVariable("id") int id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        if (property == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Property getedproperty = property.get();
        return new ResponseEntity<>(getedproperty, HttpStatus.OK);
    }*/
    public Property getProperty(@PathVariable("id") int id) {
        return propertyService.getProperty(id);
    }


    //add a property
    //accept all origins
    @CrossOrigin(origins = "*")
    @PostMapping("/add-property")
    public ResponseEntity<ApiResponse> createProperty(MultipartHttpServletRequest request) {
        System.out.println("REQUEST:");
        System.out.println(request.getParameter("property"));
        System.out.println(request.getFiles("images"));
        Gson gson = new Gson();
        Property property = gson.fromJson(request.getParameter("property"), Property.class);
        property.setPublish_date(new Date());
        System.out.println("property: " + property);
        //Get the image files from the request
        List<MultipartFile> images = request.getFiles("images");
        System.out.println("images: " + images);

        try {
            propertyService.createProperty(property, images);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    @CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
    @PostMapping("/{propertyId}/add-comment")
    public Comment addComment(@PathVariable int propertyId, @RequestBody Comment comment) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property == null) {
            throw new RuntimeException("Property not found or client not found");
        }
        comment.setProperty(property);
        comment.setCreated_at(new Date().toString());
        comment.setUpdated_at(new Date().toString());
        return commentRepository.save(comment);
    }

    //get all comment of a property
    @GetMapping("/{propertyId}/comments")
    public List<Comment> getComments(@PathVariable int propertyId) {
        return commentRepository.findByPropertyId(propertyId);
    }

    //get latest property id
    @GetMapping("/latest-property-id")
    public int getLatestPropertyId() {
        return propertyService.getLatestPropertyId();
    }

    //check the status of the property
    @GetMapping("/property/{id}/availability")
    public String checkPropertyStatus(@PathVariable int id) {
        return propertyService.checkPropertyStatus(id);
    }

    //FILITRING PROPERTIES METHODS

    //filter properties by price (min, max)
    @GetMapping("/filter-by-price")
    public List<Property> filterByPrice(@RequestParam("min") int min, @RequestParam("max") int max) {
        return propertyService.filterByPrice(min, max);
    }

    //filter properties by search
    @GetMapping("/filter-by-search")
    public List<Property> filterBySearch(@RequestParam("search") String search) {
        return propertyService.filterBySearch(search);
    }

    //filter properties by type
    @GetMapping("/filter-by-type")
    public List<Property> filterByType(@RequestParam("type") String type) {
        return propertyService.filterByType(type);
    }

    //filter properties by location
    @GetMapping("/filter-by-location")
    public List<Property> filterByLocation(@RequestParam("location") String location) {
        return propertyService.filterByLocation(location);
    }

    //filter properties by category
    @GetMapping("/filter-by-category")
    public List<Property> filterByCategory(@RequestParam("category") String category) {
        return propertyService.filterByCategory(category);
    }
}
