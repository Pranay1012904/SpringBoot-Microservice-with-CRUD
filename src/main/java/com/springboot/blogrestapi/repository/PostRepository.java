package com.springboot.blogrestapi.repository;

import com.springboot.blogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findPostById(Long id);
}
