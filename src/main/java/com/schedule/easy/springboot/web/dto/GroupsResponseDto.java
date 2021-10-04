package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.group.Group;
import lombok.Getter;

@Getter
public class GroupsResponseDto {
    private Long groupSeq;
    private String name;
    private String useYn;

    public GroupsResponseDto(Group entity) {
        this.groupSeq = entity.getSeq();
        this.name = entity.getName();
        this.useYn = entity.getUseYn();
    }
}
