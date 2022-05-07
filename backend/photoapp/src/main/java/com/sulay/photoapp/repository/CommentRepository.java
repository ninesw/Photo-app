package com.sulay.photoapp.repository;

import com.sulay.photoapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c from Comment c WHERE c.photo.id = :photoId")
    List<Comment> getCommentByPhotoId(@Param("photoId") int photoId);
}
