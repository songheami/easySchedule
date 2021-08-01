package com.schedule.easy.springboot.domain.groupHoliday;

import com.schedule.easy.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "group_holiday")
public class GroupHoliday extends BaseTimeEntity {

    @Id
    @Column(name = "group_holiday_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupHolidayNum;

    @Column(name = "group_id", length = 11, nullable = false)
    private Long groupId;

    @Column(name = "holiday", length = 10, nullable = false)
    private String holiday;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "allday_yn", length = 1)
    private String alldayYn;

    @Column(name = "start_time", length = 5)
    private String startTime;

    @Column(name = "end_time", length = 5)
    private String endTime;

    @Column(name = "use_yn", length = 1, nullable = false)
    private String useYn;

    @Builder
    public GroupHoliday(Long groupHolidayNum,
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
}
