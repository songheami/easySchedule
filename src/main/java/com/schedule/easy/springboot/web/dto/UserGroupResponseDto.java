package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.group.Group;
import com.schedule.easy.springboot.domain.role.Role;
import com.schedule.easy.springboot.domain.user.User;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import lombok.Getter;

@Getter
public class UserGroupResponseDto {

    private Long userSeq;
    private Long groupSeq;
    private Long roleSeq;
    private String useYn;
    private User user;
    private Group group;
    private Role role;

    public UserGroupResponseDto(UserGroup entity) {
        this.userSeq = entity.getUserSeq();
        this.groupSeq = entity.getGroupSeq();
        this.roleSeq = entity.getRoleSeq();
        this.useYn = entity.getUseYn();
        this.user = entity.getUser();
        this.group = entity.getGroup();
        this.role = entity.getRole();
    }
}
