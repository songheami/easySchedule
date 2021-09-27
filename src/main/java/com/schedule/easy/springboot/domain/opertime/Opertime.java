package com.schedule.easy.springboot.domain.opertime;

import com.schedule.easy.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_opertime")
public class Opertime extends BaseTimeEntity {

    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "user_seq", length = 11, nullable = false)
    private Long userSeq;

    @Column(name = "group_seq", length = 11, nullable = false)
    private Long groupSeq;

    @Column(name = "day_code", length = 50, nullable = false)
    private String dayCode;

    @Column(name = "start_time", length = 5)
    private String startTime;

    @Column(name = "end_time", length = 5)
    private String endTime;

    @Column(name = "use_yn", length = 1, nullable = false)
    private String useYn;

    @Builder
    public Opertime(Long seq
                  , Long userSeq
                  , Long groupSeq
                  , String dayCode
                  , String startTime
                  , String endTime
                  , String useYn) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.groupSeq = groupSeq;
        this.dayCode = dayCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.useYn = useYn;
    }
}
