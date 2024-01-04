package com.blog.Blogger.controller;

import com.blog.Blogger.payload.CommentDto;
import com.blog.Blogger.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestParam(name="postId") long postId,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable long postId){
        List<CommentDto> comments = commentService.getComments(postId);
        return  new ResponseEntity<>(comments,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
        List<CommentDto> commentDtos=commentService.getAllComments();
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId){
        commentService.deleteComment(commentId);
        return  new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long commentId,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateComment(commentId, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

}
//spring security helps in authorization(roles in application like which feature he can access) authentication(whether un pw is valid or not)
//after adding spring security dependency all  link s of rest api are secured
//config file helps us which url who can access and who can't