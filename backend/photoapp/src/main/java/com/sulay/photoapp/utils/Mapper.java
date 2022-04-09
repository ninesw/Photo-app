package com.sulay.photoapp.utils;

import com.sulay.photoapp.Dto.AlbumDto;
import com.sulay.photoapp.Dto.CommentDto;
import com.sulay.photoapp.Dto.PhotoDto;
import com.sulay.photoapp.entity.Album;
import com.sulay.photoapp.entity.Comment;
import com.sulay.photoapp.entity.Photo;


public class Mapper {
    public static Photo mapToPhoto(PhotoDto photoDto) {
        Photo photo = new Photo();
        photo.setName(photoDto.getName());
        photo.setDescription(photoDto.getDescription());
        photo.setUrl(photoDto.getUrl());
        photo.setId(photoDto.getId());

        return photo;
    }

    public static PhotoDto mapFromPhoto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setName(photo.getName());
        photoDto.setDescription(photo.getDescription());
        photoDto.setUrl(photo.getUrl());
        photoDto.setId(photo.getId());

        return photoDto;
    }

    public static Album mapToAlbum(AlbumDto albumDto) {
        Album album = new Album();
        album.setName(albumDto.getName());
        album.setDescription(albumDto.getDescription());
        album.setId(albumDto.getId());

        return album;
    }

    public static AlbumDto mapFromAlbum(Album album) {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setName(album.getName());
        albumDto.setThumbnailUrl(album.getThumbnailUrl());
        albumDto.setDescription(album.getDescription());
        albumDto.setId(album.getId());
        return albumDto;

    }

    public static CommentDto mapFromComment(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setMessage(comment.getMessage());
        commentDto.setId(comment.getId());

        return commentDto;
    }
}
