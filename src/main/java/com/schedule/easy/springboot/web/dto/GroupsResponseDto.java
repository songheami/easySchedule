package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.group.Groups;
import lombok.Getter;

@Getter
public class GroupsResponseDto {
    private Long groupId;
    private String name;
    private String useYn;

    public GroupsResponseDto(Groups entity) {
        this.groupId = entity.getGroupId();
        this.name = entity.getName();
        this.useYn = entity.getUseYn();
    }
}
