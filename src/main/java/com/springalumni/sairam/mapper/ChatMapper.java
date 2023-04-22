package com.springalumni.sairam.mapper;

import com.springalumni.sairam.dto.ChatDTO;
import com.springalumni.sairam.dto.DomainDTO;
import com.springalumni.sairam.models.ChatSpace;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMapper {
    private final DomainMapper domainMapper;
    public ChatSpace mapToEntity(ChatDTO chatDTO){
        ChatSpace chatSpace = new ChatSpace();
        BeanUtils.copyProperties(chatDTO, chatSpace);
        return chatSpace;
    }
    public ChatDTO mapToDTO(ChatSpace chatSpace){
        ChatDTO chatDTO = new ChatDTO();
        DomainDTO domainDTO = domainMapper.mapToDTO(chatSpace.getDomain());
        BeanUtils.copyProperties(chatSpace, chatDTO);
        chatDTO.setDomain(domainDTO);
        return chatDTO;
    }
}
