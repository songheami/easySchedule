package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.groupHoliday.GroupHoliday;
import com.schedule.easy.springboot.domain.userGroupHoliday.UserGroupHoliday;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserGroupHolidayRequestDto {

    private Long userGroupHolidayNum;
    private Long userId;
    private Long groupId;
    private String holiday;
    private String name;
    private String alldayYn;
    private String startTime;
    private String endTime;
    private String useYn;

    @Builder
    public UserGroupHolidayRequestDto(Long userGroupHolidayNum,
                                      Long userId,
                                      Long groupId,
                                      String holiday,
                                      String name,
                                      String alldayYn,
                                      String startTime,
                                      String endTime,
                                      String useYn) {
        this.userGroupHolidayNum = userGroupHolidayNum;
        this.userId = userId;
        this.groupId = groupId;
        this.holiday = holiday;
        this.name = name;
        this.alldayYn = alldayYn;
        this.startTime = startTime;
        this.endTime = endTime;
        this.useYn = useYn;
    }

    public UserGroupHoliday toEntity() {
        return UserGroupHoliday.builder()
                .userGroupHolidayNum(userGroupHolidayNum)
                .userId(groupId)
                .groupId(groupId)
                .holiday(holiday)
                .name(name)
                .alldayYn(alldayYn)
                .startTime(startTime)
                .endTime(endTime)
                .useYn(useYn)
                .build();
    }
}
