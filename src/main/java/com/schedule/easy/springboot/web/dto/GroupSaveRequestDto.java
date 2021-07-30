package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.group.Groups;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupSaveRequestDto {
    private Long groupId;
    private String name;
    private String useYn;

    @Builder
    public GroupSaveRequestDto(Long groupId, String name, String useYn) {
        this.groupId = groupId;
        this.name = name;
        this.useYn = useYn;
    }

    public Groups toEntity() {
        return Groups.builder()
                .groupId(groupId)
                .name(name)
                .useYn(useYn)
                .build();
    }

}
