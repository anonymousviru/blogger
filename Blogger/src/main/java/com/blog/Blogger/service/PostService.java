package com.blog.Blogger.service;

import com.blog.Blogger.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    void deletePost(Long id);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortby, String sortDir);


    PostDto updatePost(long postId, PostDto postDto);
}
