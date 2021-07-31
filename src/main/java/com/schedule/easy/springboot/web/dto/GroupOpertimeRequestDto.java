package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.groupOpertime.GroupOpertime;
import com.schedule.easy.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.type.OffsetTimeType;

@Getter
@NoArgsConstructor
public class GroupOpertimeRequestDto {

    private Long opertimeId;
    private Long groupId;
    private String sunStartTime = null;
    private String sunEndTime = null;
    private String monStartTime = null;
    private String monEndTime = null;
    private String tueStartTime = null;
    private String tueEndTime = null;
    private String wedStartTime = null;
    private String wedEndTime = null;
    private String thuStartTime = null;
    private String thuEndTime = null;
    private String friStartTime = null;
    private String friEndTime = null;
    private String satStartTime = null;
    private String satEndTime = null;
    private String useYn = null;

    @Builder
    public GroupOpertimeRequestDto(Long opertimeId, Long groupId,
                                   String sunStartTime, String sunEndTime,
                                   String monStartTime, String monEndTime,
                                   String tueStartTime, String tueEndTime,
                                   String wedStartTime, String wedEndTime,
                                   String thuStartTime, String thuEndTime,
                                   String friStartTime, String friEndTime,
                                   String satStartTime, String satEndTime,
                                   String useYn) {
        this.opertimeId = opertimeId;
        this.groupId = groupId;
        this.sunStartTime = sunStartTime; this.sunEndTime = sunEndTime;
        this.monStartTime = monStartTime; this.monEndTime = monEndTime;
        this.tueStartTime = tueStartTime; this.tueEndTime = tueEndTime;
        this.wedStartTime = wedStartTime; this.wedEndTime = wedEndTime;
        this.thuStartTime = thuStartTime; this.thuEndTime = thuEndTime;
        this.friStartTime = friStartTime; this.friEndTime = friEndTime;
        this.satStartTime = satStartTime; this.satEndTime = satEndTime;
        this.useYn = useYn;
    }

    public GroupOpertime toEntity() {
        return GroupOpertime.builder()
                .opertimeId(opertimeId)
                .groupId(groupId)
                .sunStartTime(sunStartTime)
                .sunEndTime(sunEndTime)
                .monStartTime(monStartTime)
                .monEndTime(monEndTime)
                .tueStartTime(tueStartTime)
                .tueEndTime(tueEndTime)
                .wedStartTime(wedStartTime)
                .wedEndTime(wedEndTime)
                .thuStartTime(thuStartTime)
                .thuEndTime(thuEndTime)
                .friStartTime(friStartTime)
                .friEndTime(friEndTime)
                .satStartTime(satStartTime)
                .satEndTime(satEndTime)
                .useYn(useYn)
                .build();
    }
    public GroupOpertime toEntity(Long groupId) {
        return GroupOpertime.builder()
                .opertimeId(opertimeId)
                .groupId(groupId)
                .sunStartTime(sunStartTime)
                .sunEndTime(sunEndTime)
                .monStartTime(monStartTime)
                .monEndTime(monEndTime)
                .tueStartTime(tueStartTime)
                .tueEndTime(tueEndTime)
                .wedStartTime(wedStartTime)
                .wedEndTime(wedEndTime)
                .thuStartTime(thuStartTime)
                .thuEndTime(thuEndTime)
                .friStartTime(friStartTime)
                .friEndTime(friEndTime)
                .satStartTime(satStartTime)
                .satEndTime(satEndTime)
                .useYn("Y")
                .build();
    }
}
