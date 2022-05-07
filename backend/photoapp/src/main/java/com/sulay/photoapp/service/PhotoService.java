package com.sulay.photoapp.service;

import com.sulay.photoapp.Dto.AlbumDto;
import com.sulay.photoapp.Dto.CommentDto;
import com.sulay.photoapp.Dto.PhotoDto;
import com.sulay.photoapp.entity.Photo;
import com.sulay.photoapp.exception.PhotoAppException;
import com.sulay.photoapp.repository.AlbumRepository;
import com.sulay.photoapp.repository.CommentRepository;
import com.sulay.photoapp.repository.PhotoRepository;
import com.sulay.photoapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;
    private final CommentRepository commmentRepository;
    private final S3FileService s3FileService;

    @Autowired
    public PhotoService(PhotoRepository photoRepository, AlbumRepository albumRepository, CommentRepository commmentRepository, S3FileService s3FileService) {
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
        this.commmentRepository = commmentRepository;
        this.s3FileService = s3FileService;
    }

    public List<PhotoDto> findAll() {
        return this.photoRepository.findAll()
                .stream()
                .map(Mapper::mapFromPhoto)
                .collect(Collectors.toList());
    }

    public PhotoDto findById(int photoId) {
        return Mapper.mapFromPhoto(this.photoRepository.findById(photoId)
                .orElseThrow(() -> new PhotoAppException("No photo with id " + photoId)));
    }

    public PhotoDto addPhoto(MultipartFile file, int albumId) {
        Photo photo = new Photo();
        photo.setAlbum(this.albumRepository.findById(albumId)
                .orElseThrow(() -> new PhotoAppException("No album with id " + albumId)));
        photo.setUrl(s3FileService.uploadFile(file));
        return Mapper.mapFromPhoto(photoRepository.save(photo));
    }

    public List<CommentDto> getComments(int photoId) {
        return this.photoRepository.getComments(photoId)
                .stream()
                .map(Mapper::mapFromComment)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> deletePhoto(int photoId) {
        Photo photoToDelete = this.photoRepository.findById(photoId)
                .orElseThrow(() -> new PhotoAppException("No photo with id " + photoId));

        if (s3FileService.deleteByUrl(photoToDelete.getUrl())) {
            this.photoRepository.delete(photoToDelete);
            return new ResponseEntity<>("Deleted photo successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("Couldn't delete photo with id " + photoId,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
