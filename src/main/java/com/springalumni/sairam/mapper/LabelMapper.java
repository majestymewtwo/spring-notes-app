package com.springalumni.sairam.mapper;

import com.springalumni.sairam.dto.LabelDTO;
import com.springalumni.sairam.models.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabelMapper {
    public LabelDTO mapToDTO(Label label){
        LabelDTO labelDTO = new LabelDTO();
        BeanUtils.copyProperties(label, labelDTO);
        return labelDTO;
    }
    public Label mapToEntity(LabelDTO labelDTO){
        Label label = new Label();
        BeanUtils.copyProperties(labelDTO, label);
        return label;
    }
}
