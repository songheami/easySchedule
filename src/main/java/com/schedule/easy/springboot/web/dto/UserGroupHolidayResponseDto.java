package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.userGroupHoliday.UserGroupHoliday;

public class UserGroupHolidayResponseDto {

    private Long userGroupHolidayNum;
    private Long userId;
    private Long groupId;
    private String holiday;
    private String name;
    private String alldayYn;
    private String startTime;
    private String endTime;
    private String useYn;

    public UserGroupHolidayResponseDto(UserGroupHoliday entity) {
        this.userGroupHolidayNum = entity.getUserGroupHolidayNum();
        this.userId = entity.getUserId();
        this.groupId = entity.getGroupId();
        this.holiday = entity.getHoliday();
        this.name = entity.getName();
        this.alldayYn = entity.getAlldayYn();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.useYn = entity.getUseYn();
    }
}
