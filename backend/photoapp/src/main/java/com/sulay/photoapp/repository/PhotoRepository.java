package com.sulay.photoapp.repository;

import com.sulay.photoapp.entity.Comment;
import com.sulay.photoapp.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Query("SELECT p.comments FROM Photo p where p.id = :id")
    List<Comment> getComments(@Param("id") int id);
}
