package com.sulay.photoapp.controller;

import com.sulay.photoapp.Dto.CommentDto;
import com.sulay.photoapp.Dto.PhotoDto;
import com.sulay.photoapp.service.CommentService;
import com.sulay.photoapp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/photos")
@RestController
public class PhotoController {

    private final PhotoService photoService;
    private final CommentService commentService;

    @Autowired
    public PhotoController(PhotoService photoservice, CommentService commentService) {
        this.photoService = photoservice;
        this.commentService = commentService;
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


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deletePhoto(@RequestParam("photoId") int photoId) {
        return this.photoService.deletePhoto(photoId);
    }

    @GetMapping("/{photoId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getComments(@PathVariable("photoId") int photoId) {
        return this.commentService.getCommentsByPhotoId(photoId);
    }

}
