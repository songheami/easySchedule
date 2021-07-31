package com.schedule.easy.springboot.domain.groupOpertime;

import com.schedule.easy.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "group_opertime")
public class GroupOpertime extends BaseTimeEntity {

    @Id
    @Column(name = "opertime_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opertimeId;

    @Column(name = "group_id", length = 11, nullable = false)
    private Long groupId;

    @Column(name = "sun_start_time", length = 5)
    private String sunStartTime;
    @Column(name = "sun_end_time", length = 5)
    private String sunEndTime;
    @Column(name = "mon_start_time", length = 5)
    private String monStartTime;
    @Column(name = "mon_end_time", length = 5)
    private String monEndTime;
    @Column(name = "tue_start_time", length = 5)
    private String tueStartTime;
    @Column(name = "tue_end_time", length = 5)
    private String tueEndTime;
    @Column(name = "wed_start_time", length = 5)
    private String wedStartTime;
    @Column(name = "wed_end_time", length = 5)
    private String wedEndTime;
    @Column(name = "thu_start_time", length = 5)
    private String thuStartTime;
    @Column(name = "thu_end_time", length = 5)
    private String thuEndTime;
    @Column(name = "fri_start_time", length = 5)
    private String friStartTime;
    @Column(name = "fri_end_time", length = 5)
    private String friEndTime;
    @Column(name = "sat_start_time", length = 5)
    private String satStartTime;
    @Column(name = "sat_end_time", length = 5)
    private String satEndTime;

    @Column(name = "use_yn", length = 1, nullable = false)
    private String useYn;

    @Builder
    public GroupOpertime(Long opertimeId, Long groupId,
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
}
