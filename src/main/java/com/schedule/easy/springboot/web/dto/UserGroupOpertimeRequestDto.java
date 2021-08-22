package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.groupOpertime.GroupOpertime;
import com.schedule.easy.springboot.domain.userGroupOpertime.UserGroupOpertime;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserGroupOpertimeRequestDto {

    private Long opertimeId;
    private Long userId;
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
    public UserGroupOpertimeRequestDto(Long opertimeId, Long groupId, Long userId,
                                       String sunStartTime, String sunEndTime,
                                       String monStartTime, String monEndTime,
                                       String tueStartTime, String tueEndTime,
                                       String wedStartTime, String wedEndTime,
                                       String thuStartTime, String thuEndTime,
                                       String friStartTime, String friEndTime,
                                       String satStartTime, String satEndTime,
                                       String useYn) {
        this.opertimeId = opertimeId;
        this.userId = userId;
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

    public UserGroupOpertime toEntity() {
        return UserGroupOpertime.builder()
                .opertimeId(opertimeId)
                .userId(userId)
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
}
