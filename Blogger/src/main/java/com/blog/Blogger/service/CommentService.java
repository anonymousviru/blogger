package com.blog.Blogger.service;

import com.blog.Blogger.payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getComments(Long id);

    void deleteComment(long commentId);



    CommentDto updateComment(long commentId, CommentDto commentDto);

    List<CommentDto> getAllComments();
}
