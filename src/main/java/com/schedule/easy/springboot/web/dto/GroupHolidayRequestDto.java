package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.groupHoliday.GroupHoliday;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupHolidayRequestDto {

    private Long groupHolidayNum;
    private Long groupId;
    private String holiday;
    private String name;
    private String alldayYn;
    private String startTime;
    private String endTime;
    private String useYn;

    @Builder
    public GroupHolidayRequestDto(Long groupHolidayNum,
                                  Long groupId,
                                  String holiday,
                                  String name,
                                  String alldayYn,
                                  String startTime,
                                  String endTime,
                                  String useYn) {
        this.groupHolidayNum = groupHolidayNum;
        this.groupId = groupId;
        this.holiday = holiday;
        this.name = name;
        this.alldayYn = alldayYn;
        this.startTime = startTime;
        this.endTime = endTime;
        this.useYn = useYn;
    }

    public GroupHoliday toEntity() {
        return GroupHoliday.builder()
                .groupHolidayNum(groupHolidayNum)
                .groupId(groupId)
                .holiday(holiday)
                .name(name)
                .alldayYn(alldayYn)
                .startTime(startTime)
                .endTime(endTime)
                .useYn(useYn)
                .build();
    }
    public GroupHoliday toEntity(Long groupId) {
        return GroupHoliday.builder()
                .groupHolidayNum(groupHolidayNum)
                .groupId(groupId)
                .holiday(holiday)
                .name(name)
                .alldayYn(alldayYn)
                .startTime(startTime)
                .endTime(endTime)
                .useYn("Y")
                .build();
    }
}
