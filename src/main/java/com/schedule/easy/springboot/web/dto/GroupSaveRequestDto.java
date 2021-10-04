package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.group.Group;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupSaveRequestDto {
    private Long seq;
    private String name;
    private String useYn;

    @Builder
    public GroupSaveRequestDto(Long seq, String name, String useYn) {
        this.seq = seq;
        this.name = name;
        this.useYn = useYn;
    }

    public Group toEntity() {
        return Group.builder()
                .seq(seq)
                .name(name)
                .useYn(useYn)
                .build();
    }

}
