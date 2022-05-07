package com.sulay.photoapp.repository;

import com.sulay.photoapp.entity.Album;
import com.sulay.photoapp.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    @Query("SELECT a.photos FROM Album a WHERE a.id = :albumId")
    List<Photo> getPhotos(@Param("albumId") int albumId);

    List<Album> getAlbumsByNameContains(@Param("keyword") String keyword);
}
