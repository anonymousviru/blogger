package com.blog.Blogger.controller;

import com.blog.Blogger.entity.Post;
import com.blog.Blogger.payload.PostDto;
import com.blog.Blogger.service.PostService;
import javax.validation.Valid;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
@PreAuthorize("hasRole('ADMIN')")
    @PostMapping

        public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindResult){//automatically eror captured
        if(bindResult.hasErrors()){
            return new ResponseEntity<>(bindResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(name="sortby",defaultValue = "id",required = false) String sortby,
            @RequestParam(name = "sortDir",defaultValue = "asc",required = false)String sortDir
    ){
        List<PostDto> postDtos=postService.getAllPosts(pageNo,pageSize,sortby,sortDir);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<PostDto> updatePost(
            @RequestParam("postId") long postId,
            @RequestBody PostDto postDto
    ){
        PostDto dto = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
//    public ResponseEntity<String> createPost(@RequestBody PostDto postDto){
//        postService.createPost(postDto);
//        return new ResponseEntity<>("Post is created", HttpStatus.CREATED);
//    }
}
