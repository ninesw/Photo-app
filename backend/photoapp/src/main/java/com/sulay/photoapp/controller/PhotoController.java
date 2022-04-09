package com.sulay.photoapp.controller;

import com.sulay.photoapp.Dto.PhotoDto;
import com.sulay.photoapp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/photos")
@RestController
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoservice) {
        this.photoService = photoservice;
    }

    @GetMapping
    public List<PhotoDto> getAllPhotos() {
        return this.photoService.findAll();
    }

    @GetMapping("/{photoId}")
    public PhotoDto getPhoto(@PathVariable int photoId) {
        return this.photoService.findById(photoId);
    }

    @PostMapping
    public PhotoDto addPhoto(@RequestParam("file") MultipartFile file, @RequestParam("albumId") int albumId) {
        return this.photoService.addPhoto(file, albumId);
    }

    @PutMapping
    public PhotoDto editPhoto(@RequestBody PhotoDto photoDto) {
        return this.photoService.editPhoto(photoDto);
    }

}
