package com.springalumni.sairam.mapper;

import com.springalumni.sairam.dto.RepliesDTO;
import com.springalumni.sairam.dto.UserDTO;
import com.springalumni.sairam.models.Replies;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepliesMapper {
    private final UserMapper userMapper;
    public Replies mapToEntity(RepliesDTO repliesDTO){
        Replies replies = new Replies();
        BeanUtils.copyProperties(repliesDTO, replies);
        return replies;
    }
    public RepliesDTO mapToDTO(Replies replies){
        RepliesDTO repliesDTO = new RepliesDTO();
        UserDTO userDTO = userMapper.mapToDTO(replies.getUser());
        BeanUtils.copyProperties(replies, repliesDTO);
        repliesDTO.setUser(userDTO);
        return repliesDTO;
    }
}
