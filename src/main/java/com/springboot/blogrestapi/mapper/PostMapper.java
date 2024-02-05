package com.springboot.blogrestapi.mapper;

import com.springboot.blogrestapi.dto.PostDto;
import com.springboot.blogrestapi.entity.Post;

public class PostMapper {

    public static PostDto eTd(Post savedPost){
        PostDto postDto1=new PostDto(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getDescription(),
                savedPost.getDescription()
        );
        return postDto1;
    }

    public static Post dTe(PostDto postDto){
        Post post=new Post(
                null,
                postDto.getTitle(),
                postDto.getContent(),
                postDto.getDescription()
        );
        return post;
    }
}
