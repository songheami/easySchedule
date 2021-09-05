package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.opertime.Opertime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OpertimeRequestDto {

    private Long opertimeId;
    private Long userId;
    private Long groupId;
    private String dayCode;
    private String startTime;
    private String endTime;
    private String useYn;

    @Builder
    public OpertimeRequestDto(Long opertimeId
                            , Long groupId
                            , Long userId
                            , String dayCode
                            , String startTime
                            , String endTime
                            , String useYn) {
        this.opertimeId = opertimeId;
        this.groupId = groupId;
        this.userId = userId;
        this.dayCode = dayCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.useYn = useYn;
    }

    public Opertime toEntity() {
        return Opertime.builder()
                .opertimeId(opertimeId)
                .userId(userId)
                .groupId(groupId)
                .dayCode(dayCode)
                .startTime(startTime)
                .endTime(endTime)
                .useYn(useYn)
                .build();
    }
}
