package com.springalumni.sairam.mapper;

import com.springalumni.sairam.dto.PostDTO;
import com.springalumni.sairam.models.Posts;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    public Posts mapToEntity(PostDTO postDTO){
        Posts posts = new Posts();
        BeanUtils.copyProperties(postDTO, posts);
        return posts;
    }
    public PostDTO mapToDTO(Posts posts){
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(posts, postDTO);
        return postDTO;
    }
}
