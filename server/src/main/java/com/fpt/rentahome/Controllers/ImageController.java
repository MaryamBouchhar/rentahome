package com.fpt.rentahome.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/uploads")
public class ImageController {
    @GetMapping("/images/{imageName:.+}")
    public Resource getImage(@PathVariable String imageName) throws Exception {
        Path imagePath = Paths.get("src/main/webapp/uploads/images/" + imageName);
        return new UrlResource(imagePath.toUri());
    }
}
