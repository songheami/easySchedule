package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.userGroup.UserGroup;

public class UserGroupRequestDto {
    private Long user_id;
    private Long group_id;
    private Long role_id;
    private String use_yn;

    public UserGroupRequestDto(UserGroup entity) {
        this.user_id = entity.getUserId();
        this.group_id = entity.getGroupId();
        this.role_id = entity.getRoleId();
        this.use_yn = entity.getUseYn();
    }
}
