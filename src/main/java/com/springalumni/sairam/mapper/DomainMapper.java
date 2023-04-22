package com.springalumni.sairam.mapper;

import com.springalumni.sairam.dto.DomainDTO;
import com.springalumni.sairam.models.Domain;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DomainMapper {
    public Domain mapToEntity(DomainDTO domainDTO){
        Domain domain = new Domain();
        BeanUtils.copyProperties(domainDTO, domain);
        return domain;
    }
    public DomainDTO mapToDTO(Domain domain){
        DomainDTO domainDTO = new DomainDTO();
        BeanUtils.copyProperties(domain, domainDTO);
        return domainDTO;
    }
}
