package com.sulay.photoapp.service;

import com.sulay.photoapp.Dto.CommentDto;
import com.sulay.photoapp.entity.Comment;
import com.sulay.photoapp.entity.Photo;
import com.sulay.photoapp.exception.PhotoAppException;
import com.sulay.photoapp.repository.CommentRepository;
import com.sulay.photoapp.repository.PhotoRepository;
import com.sulay.photoapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PhotoRepository photoRepository) {
        this.commentRepository = commentRepository;
        this.photoRepository = photoRepository;
    }

    public List<CommentDto> getCommentsByPhotoId(int photoId) {
        return commentRepository.getCommentByPhotoId(photoId)
                .stream()
                .map(Mapper::mapFromComment)
                .collect(Collectors.toList());
    }

    public CommentDto addComment(CommentDto commentDto) {
        Photo photo = photoRepository.findById(commentDto.getPhotoId()).orElseThrow(
                () -> new PhotoAppException("No Photo with id " + commentDto.getPhotoId()));

        Comment comment = new Comment();
        comment.setId(comment.getId());
        comment.setPhoto(photo);
        comment.setMessage(commentDto.getMessage());

        return Mapper.mapFromComment(commentRepository.save(comment));
    }

    public List<CommentDto> getComments() {
        return this.commentRepository.findAll()
                .stream()
                .map(Mapper::mapFromComment)
                .collect(Collectors.toList());
    }

    public void deleteComment(int commentId) {
        this.commentRepository.deleteById(commentId);
    }
}
