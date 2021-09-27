package com.schedule.easy.springboot.domain.schedule;

import com.schedule.easy.springboot.domain.BaseTimeEntity;
import com.schedule.easy.springboot.domain.user.User;
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
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "memb_seq", nullable = false)
    private Long membSeq;

    @Column(name = "staff_seq", nullable = false)
    private Long staffSeq;

    @Column(name = "stat_code", nullable = false)
    private String statCode;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "end_time", nullable = false)
    private String endTime;

    @Builder
    public Schedule(Long seq,
                    Long membSeq,
                    Long staffSeq,
                    String statCode,
                    String title,
                    String startTime,
                    String endTime) {
        this.seq = seq;
        this.membSeq = membSeq;
        this.staffSeq = staffSeq;
        this.statCode = statCode;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule update(String statCode, Long staffSeq, String title,
                           String startTime, String endTime) {
        this.statCode = statCode;
        this.staffSeq = staffSeq;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        return this;
    }
}
