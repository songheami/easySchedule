package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserGroupRequestDto {
    private Long userSeq;
    private Long groupSeq;
    private Long roleSeq;
    private String useYn;

    public UserGroupRequestDto(UserGroup entity) {
        this.userSeq = entity.getUserSeq();
        this.groupSeq = entity.getGroupSeq();
        this.roleSeq = entity.getRoleSeq();
        this.useYn = entity.getUseYn();
    }

    public UserGroup toEntity() {
        return UserGroup.builder()
                .userSeq(userSeq)
                .groupSeq(groupSeq)
                .roleSeq(roleSeq)
                .useYn(useYn)
                .build();
    }
}
