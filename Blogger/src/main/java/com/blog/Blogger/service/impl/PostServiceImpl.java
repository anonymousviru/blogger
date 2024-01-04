package com.blog.Blogger.service.impl;

import com.blog.Blogger.entity.Post;
import com.blog.Blogger.exception.ResourceNotFoundException;
import com.blog.Blogger.payload.PostDto;
import com.blog.Blogger.repository.PostRepository;
import com.blog.Blogger.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post =new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savedPost = postRepo.save(post);
        PostDto dto = new PostDto();
        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());
        return dto;
    }

    @Override
    public void deletePost(Long id) {
         postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id " + id)

        );
        postRepo.deleteById(id);

    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortby, String sortDir) {
       Sort sort= (sortDir.equalsIgnoreCase(Sort.Direction.ASC.toString()))?
               Sort.by(sortby).ascending()
               :Sort.by(sortby).descending();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pagePosts = postRepo.findAll(pageable);
        List<Post> posts = pagePosts.getContent();
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () ->
                        new ResourceNotFoundException("Post not found with id " + postId)
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    PostDto mapToDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }
}
//sir while deleting a resource.during checking if the resource is not present then i want to throw an exception.for that i use lamba expression to throw custom exception or by optional class