package com.springboot.blogrestapi.controller;

import com.springboot.blogrestapi.dto.PostDto;
import com.springboot.blogrestapi.dto.PostResponse;
import com.springboot.blogrestapi.entity.Post;
import com.springboot.blogrestapi.service.PostService;
import com.springboot.blogrestapi.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createAPost(@RequestBody PostDto post){
       return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED) ;
    }
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = AppConstants.PAGE_NUMBER, defaultValue = AppConstants.PAGE_NUMBER_DEFAULT, required = false) int pageNo,
            @RequestParam(value = AppConstants.PAGE_SIZE, defaultValue = AppConstants.PAGE_SIZE_DEFAULT, required = false) int pageSize
    ){
        return postService.getAllPosts(pageNo, pageSize);
    }
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
           PostDto fetchedPost= postService.getPostById(id);
           return new ResponseEntity<>(fetchedPost,HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable long id){
       return new ResponseEntity<>(postService.updatePost(postDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> detelePost(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>(String.format("POST WIT ID %s DELETED!",id),HttpStatus.OK);
    }

}
