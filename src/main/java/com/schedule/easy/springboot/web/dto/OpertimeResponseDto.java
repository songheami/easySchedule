package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.opertime.Opertime;
import lombok.Getter;

@Getter
public class OpertimeResponseDto {

    private Long seq;
    private Long userSeq;
    private Long groupSeq;
    private String dayCode;
    private String startTime;
    private String endTime;
    private String useYn;

    public OpertimeResponseDto(Opertime entity) {
        this.seq = entity.getSeq();
        this.userSeq = entity.getUserSeq();
        this.groupSeq = entity.getGroupSeq();
        this.dayCode = entity.getDayCode();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.useYn = entity.getUseYn();
    }
}
