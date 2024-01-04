package com.blog.Blogger.service.impl;

import com.blog.Blogger.entity.Comment;
import com.blog.Blogger.entity.Post;
import com.blog.Blogger.exception.ResourceNotFoundException;
import com.blog.Blogger.payload.CommentDto;
import com.blog.Blogger.repository.CommentRepository;
import com.blog.Blogger.repository.PostRepository;
import com.blog.Blogger.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
//@Autowired and constructor injection who is better what are aadvantage write ways to do dependencyinjection
    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post is not found with id " + postId));
        Comment comment=new Comment();
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setPost(post);
        System.out.println(post);
        Comment saved = commentRepository.save(comment);
        CommentDto dto= new CommentDto();
        dto.setBody(saved.getBody());
        dto.setEmail(saved.getEmail());
        dto.setName(saved.getName());
        dto.setId(saved.getId());

        return dto;
    }

    @Override
    public List<CommentDto> getComments(Long postId) {
        List<Comment> byPostId = commentRepository.findByPostId(postId);
        List<CommentDto> commentDtos = byPostId.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("not found"));
        commentRepository.deleteById(commentId);
    }



    @Override
    public CommentDto updateComment(long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("not found"));

        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments=commentRepository.findAll();
        List<CommentDto> commentDtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());

        return commentDtos;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto= new CommentDto();
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());
        dto.setName(comment.getName());
        dto.setId(comment.getId());

        return dto;
    }
}
