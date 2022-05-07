package com.sulay.photoapp.Dto;

import lombok.Data;

@Data
public class CommentDto {
    private int id;
    private String message;
    private int photoId;
}
