package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.posts.Posts;
import com.schedule.easy.springboot.domain.schedule.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private Long seq;
    private Long membSeq;
    private Long staffSeq;
    private String statCode;
    private String title;
    private String startTime;
    private String endTime;

    public ScheduleResponseDto(Schedule entity) {
        this.seq = entity.getSeq();
        this.membSeq = entity.getMembSeq();
        this.staffSeq = entity.getStaffSeq();
        this.statCode = entity.getStatCode();
        this.title = entity.getTitle();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
    }
}
