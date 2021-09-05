package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.opertime.Opertime;
import lombok.Getter;

@Getter
public class OpertimeResponseDto {

    private Long opertimeId;
    private Long userId;
    private Long groupId;
    private String dayCode;
    private String startTime;
    private String endTime;
    private String useYn;

    public OpertimeResponseDto(Opertime entity) {
        this.opertimeId = entity.getOpertimeId();
        this.userId = entity.getUserId();
        this.groupId = entity.getGroupId();
        this.dayCode = entity.getDayCode();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.useYn = entity.getUseYn();
    }
}
