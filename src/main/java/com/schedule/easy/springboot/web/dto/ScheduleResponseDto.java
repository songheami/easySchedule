package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.posts.Posts;
import com.schedule.easy.springboot.domain.schedule.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private Long scheduleId;
    private Long memberId;
    private Long staffId;
    private String statCode;
    private String title;
    private String startTime;
    private String endTime;

    public ScheduleResponseDto(Schedule entity) {
        this.scheduleId = entity.getScheduleId();
        this.memberId = entity.getMemberId();
        this.staffId = entity.getStaffId();
        this.statCode = entity.getStatCode();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
    }
}
