package com.blog.Blogger.repository;

import com.blog.Blogger.entity.Comment;
import com.blog.Blogger.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);
}
