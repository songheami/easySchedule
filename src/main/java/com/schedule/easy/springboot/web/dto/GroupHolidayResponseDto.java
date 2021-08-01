package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.group.Groups;
import com.schedule.easy.springboot.domain.groupHoliday.GroupHoliday;
import com.schedule.easy.springboot.domain.groupOpertime.GroupOpertime;
import org.hibernate.type.OffsetTimeType;

public class GroupHolidayResponseDto {

    private Long groupHolidayNum;
    private Long groupId;
    private String holiday;
    private String name;
    private String alldayYn;
    private String startTime;
    private String endTime;
    private String useYn;

    public GroupHolidayResponseDto(GroupHoliday entity) {
        this.groupHolidayNum = entity.getGroupHolidayNum();
        this.groupId = entity.getGroupId();
        this.holiday = entity.getHoliday();
        this.name = entity.getName();
        this.alldayYn = entity.getAlldayYn();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.useYn = entity.getUseYn();
    }
}
