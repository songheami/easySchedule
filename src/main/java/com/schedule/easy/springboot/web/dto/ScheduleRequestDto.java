package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleRequestDto {

    private Long seq;
    private Long membSeq;
    private Long staffSeq;
    private String statCode;
    private String title;
    private String startTime;
    private String endTime;

    /* 조회용 변수 */
    private String searchStartTime;
    private String searchEndTime;

    @Builder
    public ScheduleRequestDto(Long seq
                            , Long membSeq
                            , Long staffSeq
                            , String statCode
                            , String title
                            , String startTime
                            , String endTime) {
        this.seq = seq;
        this.membSeq = membSeq;
        this.staffSeq = staffSeq;
        this.statCode = statCode;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule toEntity() {
        return Schedule.builder()
                .seq(seq)
                .membSeq(membSeq)
                .staffSeq(staffSeq)
                .statCode(statCode)
                .title(title)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
