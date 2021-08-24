package com.schedule.easy.springboot.domain.reserve;

import com.schedule.easy.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Reserve extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id", nullable = false)
    private Long reserveId;

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
    public Reserve(Long reserveId,
                   Long memberId,
                   Long staffId,
                   String statCode,
                   String startTime,
                   String endTime) {
        this.reserveId = reserveId;
        this.memberId = memberId;
        this.staffId = staffId;
        this.staffId = staffId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
