package com.blog.Blogger.repository;

import com.blog.Blogger.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
