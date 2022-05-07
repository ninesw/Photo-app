package com.sulay.photoapp.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sulay.photoapp.Dto.AlbumDto;
import com.sulay.photoapp.Dto.PhotoDto;
import com.sulay.photoapp.exception.PhotoAppException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@Service
@AllArgsConstructor
public class S3FileService {

    private final AmazonS3Client s3Client;
    private final String BUCKET_NAME = "photo-app-bucket123";

    public String uploadFile(MultipartFile photoFile) {
        String fileExt = StringUtils.getFilenameExtension(photoFile.getOriginalFilename());
        String fileKey = UUID.randomUUID().toString() + fileExt;

        ObjectMetadata fileMetadata = new ObjectMetadata();
        fileMetadata.setContentLength(photoFile.getSize());
        fileMetadata.setContentType(photoFile.getContentType());
        try {
            s3Client.putObject(BUCKET_NAME, fileKey, photoFile.getInputStream(), fileMetadata);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        s3Client.setObjectAcl(BUCKET_NAME, fileKey, CannedAccessControlList.PublicRead);

        return s3Client.getResourceUrl(BUCKET_NAME, fileKey);
    }

    public boolean deleteByUrl(String thumbnailUrl) {
        AmazonS3URI awsUri = new AmazonS3URI(thumbnailUrl);
        s3Client.deleteObject(awsUri.getBucket(), awsUri.getKey());

        return true;
    }
}
