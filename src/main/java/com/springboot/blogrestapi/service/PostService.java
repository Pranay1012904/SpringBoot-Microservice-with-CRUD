package com.springboot.blogrestapi.service;

import com.springboot.blogrestapi.dto.PostDto;
import com.springboot.blogrestapi.dto.PostResponse;
import com.springboot.blogrestapi.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto, Long id);
    void deletePostById(Long id);
}
