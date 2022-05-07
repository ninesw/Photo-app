package com.sulay.photoapp.controller;

import com.sulay.photoapp.Dto.AlbumDto;
import com.sulay.photoapp.Dto.PhotoDto;
import com.sulay.photoapp.Dto.UploadAlbumRequest;
import com.sulay.photoapp.Dto.UploadAlbumResponse;
import com.sulay.photoapp.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/albums")
@RestController
public class AlbumController {

    /*
    TODO: implement edit photo cover
     */

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<AlbumDto> getAlbums() {
        return this.albumService.getAlbums();
    }

    @GetMapping("/{albumId}")
    public AlbumDto getAlbum(@PathVariable int albumId) {
        return this.albumService.findById(albumId);
    }

    @GetMapping("/search")
    public List<AlbumDto> getAlbumByKeyword(@RequestParam("keyword") String keyword) {
        return this.albumService.getAlbumByKeyword(keyword);
    }

    @GetMapping("/{albumId}/photos")
    public List<PhotoDto> getAlbumPhotos(@PathVariable int albumId) {
        return this.albumService.getPhotos(albumId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AlbumDto editAlbum(@RequestBody AlbumDto albumDto) {
        return this.albumService.editAlbum(albumDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UploadAlbumResponse addAlbum(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
                                        @RequestParam("desc") String desc) {
        UploadAlbumRequest uploadAlbumRequest = new UploadAlbumRequest();
        uploadAlbumRequest.setDescription(desc);
        uploadAlbumRequest.setName(name);
        return this.albumService.addAlbum(file, uploadAlbumRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public ResponseEntity<String> deleteAlbum(@RequestParam("albumId") int albumId) {
        return this.albumService.deleteAlbum(albumId);
    }

}
