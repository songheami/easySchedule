package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.posts.Posts;
import com.schedule.easy.springboot.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleRequestDto {

    private Long scheduleId;
    private Long memberId;
    private Long staffId;
    private String statCode;
    private String startTime;
    private String endTime;

    /* 조회용 변수 */
    private String searchStartTime;
    private String searchEndTime;

    @Builder
    public ScheduleRequestDto(Long scheduleId
                            , Long memberId
                            , Long staffId
                            , String statCode
                            , String startTime
                            , String endTime) {
        this.scheduleId = scheduleId;
        this.memberId = memberId;
        this.staffId = staffId;
        this.statCode = statCode;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule toEntity() {
        return Schedule.builder()
                .scheduleId(scheduleId)
                .memberId(memberId)
                .staffId(staffId)
                .statCode(statCode)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
