package com.sulay.photoapp.service;

import com.sulay.photoapp.Dto.AlbumDto;
import com.sulay.photoapp.Dto.PhotoDto;
import com.sulay.photoapp.Dto.UploadAlbumRequest;
import com.sulay.photoapp.Dto.UploadAlbumResponse;
import com.sulay.photoapp.entity.Album;
import com.sulay.photoapp.exception.PhotoAppException;
import com.sulay.photoapp.repository.AlbumRepository;
import com.sulay.photoapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final S3FileService s3FileService;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, S3FileService s3FileService) {
        this.albumRepository = albumRepository;
        this.s3FileService = s3FileService;
    }

    public List<AlbumDto> getAlbums() {
        return this.albumRepository.findAll()
                .stream()
                .map(Mapper::mapFromAlbum)
                .collect(Collectors.toList());
    }

    public AlbumDto findById(int albumId) {
        return Mapper.mapFromAlbum(this.albumRepository.findById(albumId)
                .orElseThrow(() -> new PhotoAppException("No album with id " + albumId)));
    }

    public List<PhotoDto> getPhotos(int albumId) {
        return this.albumRepository.getPhotos(albumId)
                .stream()
                .map(Mapper::mapFromPhoto)
                .collect(Collectors.toList());
    }

    public AlbumDto editAlbum(AlbumDto albumDto) {
        Album album = this.albumRepository.findById(albumDto.getId())
                .orElseThrow(() -> new PhotoAppException("No album with id " + albumDto.getId()));

        album.setDescription(albumDto.getDescription());
        album.setName(albumDto.getName());

        return Mapper.mapFromAlbum(this.albumRepository.save(album));
    }

    public UploadAlbumResponse addAlbum(MultipartFile file, UploadAlbumRequest uploadAlbumRequest) {
        Album album = new Album();
        album.setThumbnailUrl(this.s3FileService.uploadFile(file));
        album.setDescription(uploadAlbumRequest.getDescription());
        album.setName(uploadAlbumRequest.getName());
        Album newAlbum = this.albumRepository.save(album);

        UploadAlbumResponse response = new UploadAlbumResponse();
        response.setAlbumId(newAlbum.getId());
        response.setAlbumThumbnailUrl(newAlbum.getThumbnailUrl());

        return response;
    }

    public List<AlbumDto> getAlbumByKeyword(String keyword) {
        return this.albumRepository.getAlbumsByNameContains(keyword)
                .stream()
                .map(Mapper::mapFromAlbum)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> deleteAlbum(int albumId) {
        Album albumToDelete = this.albumRepository.findById(albumId)
                .orElseThrow(() -> new PhotoAppException("No album with id " + albumId));
        boolean deleted = this.s3FileService.deleteByUrl(albumToDelete.getThumbnailUrl());
        if (deleted) {
            this.albumRepository.delete(albumToDelete);
            return new ResponseEntity<>("Deleted album " + albumToDelete.getName(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Failed to delete album" + albumToDelete.getName(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
