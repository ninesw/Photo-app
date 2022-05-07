package com.sulay.photoapp.utils;

import com.sulay.photoapp.Dto.AlbumDto;
import com.sulay.photoapp.Dto.CommentDto;
import com.sulay.photoapp.Dto.PhotoDto;
import com.sulay.photoapp.entity.Album;
import com.sulay.photoapp.entity.Comment;
import com.sulay.photoapp.entity.Photo;


public class Mapper {

    public static PhotoDto mapFromPhoto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setUrl(photo.getUrl());
        photoDto.setId(photo.getId());

        return photoDto;
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
        commentDto.setPhotoId(comment.getPhoto().getId());

        return commentDto;
    }
}
