package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.group.Groups;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import lombok.Getter;

@Getter
public class UserGroupResponseDto {

    private Long userId;
    private Long groupId;
    private Long roleId;
    private String useYn;
    private Groups groups;

    public UserGroupResponseDto(UserGroup entity) {
        this.userId = entity.getUserId();
        this.groupId = entity.getGroupId();
        this.roleId = entity.getRoleId();
        this.useYn = entity.getUseYn();
        this.groups = entity.getGroups();
    }
}