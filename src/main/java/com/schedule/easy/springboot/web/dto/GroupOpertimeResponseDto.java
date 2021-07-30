package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.group.Groups;
import com.schedule.easy.springboot.domain.groupOpertime.GroupOpertime;
import org.hibernate.type.OffsetTimeType;

public class GroupOpertimeResponseDto {

    private Long opertimeId;
    private Long groupId;
    private String sunStartTime;
    private String sunEndTime;
    private String monStartTime;
    private String monEndTime;
    private String tueStartTime;
    private String tueEndTime;
    private String wedStartTime;
    private String wedEndTime;
    private String thuStartTime;
    private String thuEndTime;
    private String friStartTime;
    private String friEndTime;
    private String satStartTime;
    private String satEndTime;
    private String useYn;

    public GroupOpertimeResponseDto(GroupOpertime entity) {
        this.opertimeId = entity.getOpertimeId();
        this.groupId = entity.getGroupId();
        this.sunStartTime = entity.getSunStartTime();
        this.sunEndTime = entity.getSunEndTime();
        this.monStartTime = entity.getMonStartTime();
        this.monEndTime = entity.getMonEndTime();
        this.tueStartTime = entity.getTueStartTime();
        this.tueEndTime = entity.getTueEndTime();
        this.wedStartTime = entity.getWedStartTime();
        this.wedEndTime = entity.getWedEndTime();
        this.thuStartTime = entity.getThuStartTime();
        this.thuEndTime = entity.getThuEndTime();
        this.friStartTime = entity.getFriStartTime();
        this.friEndTime = entity.getFriEndTime();
        this.satStartTime = entity.getSatStartTime();
        this.satEndTime = entity.getSatEndTime();
        this.useYn = entity.getUseYn();
    }
}
