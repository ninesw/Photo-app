package com.sulay.photoapp.controller;

import com.sulay.photoapp.Dto.CommentDto;
import com.sulay.photoapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getComments() {
        return this.commentService.getComments();
    }

    @PostMapping
    public CommentDto addComment(@RequestBody CommentDto commentDto) {
        return this.commentService.addComment(commentDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") int commentId) {
        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>("Deleted comment with id " + commentId, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
