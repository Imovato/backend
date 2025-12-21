package com.unipampa.crud.resources;

import com.unipampa.crud.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageResource {

    private final ImageService imageService;

    public ImageResource(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<List<String>> upload(@RequestParam("files") List<MultipartFile> files) {
        List<String> urls = imageService.uploadImages(files);
        return ResponseEntity.ok(urls);
    }
}
