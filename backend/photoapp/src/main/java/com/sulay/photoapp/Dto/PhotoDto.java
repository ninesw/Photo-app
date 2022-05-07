package com.sulay.photoapp.Dto;

import com.sulay.photoapp.entity.Comment;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PhotoDto {
    private int id;
    private String url;
}
