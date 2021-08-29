package com.schedule.easy.springboot.domain.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.staffId IN (:staffIdList)" +
            " AND (s.startTime BETWEEN :searchStartTime AND :searchEndTime" +
            " OR s.endTime BETWEEN :searchStartTime AND :searchEndTime)")
    List<Schedule> findListBySearchTime(@Param("staffIdList") List<Long> staffIdList,
                                        @Param("searchStartTime") String searchStartTime,
                                        @Param("searchEndTime") String searchEndTime);
}
