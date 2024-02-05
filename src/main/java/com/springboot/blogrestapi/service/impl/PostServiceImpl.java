package com.springboot.blogrestapi.service.impl;

import com.springboot.blogrestapi.dto.PostDto;
import com.springboot.blogrestapi.entity.Post;
import com.springboot.blogrestapi.exception.ResourceNotFoundException;
import com.springboot.blogrestapi.mapper.PostMapper;
import com.springboot.blogrestapi.repository.PostRepository;
import com.springboot.blogrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    PostRepository postRepository;
    Logger LOGGER= LoggerFactory.getLogger(PostServiceImpl.class);

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post= PostMapper.dTe(postDto);
       Post savedPost= postRepository.save(post);
       PostDto postDto1=PostMapper.eTd(savedPost);
        return postDto1;
    }

    @Override
    public List<PostDto> getAllPosts() {
       List<Post> allPosts= postRepository.findAll();
      return allPosts.stream().map(PostMapper::eTd).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
       Post fetchedPost= postRepository.findPostById(id).orElseThrow(()-> new ResourceNotFoundException("POST","ID",id.toString()));
        return PostMapper.eTd(fetchedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post=postRepository.findPostById(id).orElseThrow(()-> new ResourceNotFoundException("Post","ID",id.toString()));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(post.getContent());
         return PostMapper.eTd(postRepository.save(post));
    }

    @Override
    public void deletePostById(Long id) {
        try {
            postRepository.deleteById(id);
        }catch(Exception ex){
            LOGGER.error(String.format("EXECPTION OCCURED DURING DELETION ::%s", ex.getMessage()));
        }
    }
}
