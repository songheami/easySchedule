package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.opertime.Opertime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OpertimeRequestDto {

    private Long seq;
    private Long userSeq;
    private Long groupSeq;
    private String dayCode;
    private String startTime;
    private String endTime;
    private String useYn;

    @Builder
    public OpertimeRequestDto(Long seq
                            , Long groupSeq
                            , Long userSeq
                            , String dayCode
                            , String startTime
                            , String endTime
                            , String useYn) {
        this.seq = seq;
        this.groupSeq = groupSeq;
        this.userSeq = userSeq;
        this.dayCode = dayCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.useYn = useYn;
    }

    public Opertime toEntity() {
        return Opertime.builder()
                .seq(seq)
                .userSeq(userSeq)
                .groupSeq(groupSeq)
                .dayCode(dayCode)
                .startTime(startTime)
                .endTime(endTime)
                .useYn(useYn)
                .build();
    }
}
