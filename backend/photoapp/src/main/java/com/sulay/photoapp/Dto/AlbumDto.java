package com.sulay.photoapp.Dto;

import com.sulay.photoapp.entity.Photo;
import lombok.Data;

import java.util.List;

@Data
public class AlbumDto {
    private int id;
    private String thumbnailUrl;
    private String name;
    private String description;
}
