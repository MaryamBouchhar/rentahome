package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Helpers.PriceRange;
import com.fpt.rentahome.Models.*;
import com.fpt.rentahome.Repositories.ClientRepository;
import com.fpt.rentahome.Repositories.CommentRepository;
import com.fpt.rentahome.Repositories.PropertyRepository;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Services.ImageService;
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
@CrossOrigin
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

    @Autowired
    private ImageService imageService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //fetch all properties
    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
    @GetMapping("/properties")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }


    //get property
    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
    @GetMapping("/properties/{id}")
    public Property getProperty(@PathVariable("id") int id) {
        return propertyService.getProperty(id);
    }


    //add a property
    //accept all origins
    @CrossOrigin(origins = "http://localhost:5174")
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
    @CrossOrigin(origins = "http://localhost:5174")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProperty(@RequestBody Property property) {
        // First, retrieve the existing property object by ID from the database
        Optional<Property> optionalProperty = propertyRepository.findById(property.getId());
        if (!optionalProperty.isPresent()) {
            // If the property does not exist, return a 404 error
            return ResponseEntity.notFound().build();
        }

        // Update the existing property object with the new values
        Property existingProperty = optionalProperty.get();
        Location location = existingProperty.getLocation();
        location.setAddress(property.getLocation().getAddress());
        location.setCity(property.getLocation().getCity());

        location.setLongitude(property.getLocation().getLongitude());
        location.setLatitude(property.getLocation().getLatitude());

        existingProperty.setLocation(location);
        existingProperty.setPrice(property.getPrice());
        existingProperty.setArea(property.getArea());
        existingProperty.setBathroom_count(property.getBathroom_count());
        existingProperty.setRent_type(property.getRent_type());
        existingProperty.setCategory(property.getCategory());
        existingProperty.setDescription(property.getDescription());

        // update the property object in the database
        propertyRepository.save(existingProperty);

        return new ResponseEntity<>(new ApiResponse(true, "updated the property"), HttpStatus.OK);
    }

    //delete property
    @CrossOrigin(origins = "http://localhost:5174")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProperty(@PathVariable("id") int id) {
        //delete images of the property
        Property property = propertyRepository.findById(id).orElse(null);
        if (property == null) {
            throw new RuntimeException("Property not found");
        }
        //delete images of the property
        imageService.deleteImagesOfProperty(property);

        propertyService.deleteProperty(id);
        return new ResponseEntity<>(new ApiResponse(true, "deleted the property"), HttpStatus.OK);
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

    //get rating of a property
    @GetMapping("/property/{id}/rating")
    public int getPropertyRating(@PathVariable int id) {
        List<Comment> comments = commentRepository.findByPropertyId(id);
        System.out.println("comments: " + comments.size());
        if (comments.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (Comment comment : comments) {
            sum += comment.getRating();
            System.out.println("Comment: " + comment.getRating());
        }
        return (int) Math.round(sum / comments.size());
    }

    //get total of rates of a property
    @GetMapping("/property/{id}/total-rates")
    public int getTotalRates(@PathVariable int id) {
        List<Comment> comments = commentRepository.findByPropertyId(id);
        return comments.size();
    }

    //FILTRING PROPERTIES METHODS

    //filter properties by price(min, max)
    @PostMapping("/filter-by-price")
    public List<Property> filterByPrice(@RequestBody PriceRange priceRange) {
        float min = priceRange.getMin_price();
        float max = priceRange.getMax_price();
        return propertyRepository.findByPriceRange(min, max);
    }

    //filter properties by category
    @PostMapping("/filter-by-category")
    public List<Property> filterByCategory(@RequestBody String category) {
        return propertyService.filterByCategory(category);
    }

    //filter properties by location
    @PostMapping("/filter-by-city")
    public List<Property> filterByLocation(@RequestBody String location) {
        return propertyService.filterByLocation(location);
    }

    //filter properties by search
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/filter-by-search")
    public List<Property> filterBySearch(@RequestBody String search) {
        System.out.println("search: " + search);
        return propertyRepository.filterBySearch(search);
    }

    //filter properties by rating
    @PostMapping("/filter-by-rating")
    public List<Property> filterByRating(@RequestParam("rating") int rating) {
        return propertyRepository.filterByRating(rating);
    }

    //get all cities from location table
    @GetMapping("/cities")
    public List<String> getAllCities() {
        return propertyRepository.getAllCities();
    }
}
