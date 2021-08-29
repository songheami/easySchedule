package com.schedule.easy.springboot.domain.schedule;

import com.schedule.easy.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_schedule")
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "staff_id", nullable = false)
    private Long staffId;

    @Column(name = "stat_code", nullable = false)
    private String statCode;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "end_time", nullable = false)
    private String endTime;

    @Builder
    public Schedule(Long scheduleId,
                    Long memberId,
                    Long staffId,
                    String statCode,
                    String startTime,
                    String endTime) {
        this.scheduleId = scheduleId;
        this.memberId = memberId;
        this.staffId = staffId;
        this.statCode = statCode;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}