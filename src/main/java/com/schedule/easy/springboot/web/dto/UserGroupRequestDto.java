package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGroupRequestDto {
    private Long userId;
    private Long groupId;
    private Long roleId;
    private String useYn;

    public UserGroupRequestDto(UserGroup entity) {
        this.userId = entity.getUserId();
        this.groupId = entity.getGroupId();
        this.roleId = entity.getRoleId();
        this.useYn = entity.getUseYn();
    }

    public UserGroup toEntity() {
        return UserGroup.builder()
                .userId(userId)
                .groupId(groupId)
                .roleId(roleId)
                .useYn(useYn)
                .build();
    }
}
