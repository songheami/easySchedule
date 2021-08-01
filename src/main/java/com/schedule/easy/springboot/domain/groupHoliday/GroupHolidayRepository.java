package com.schedule.easy.springboot.domain.groupHoliday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupHolidayRepository extends JpaRepository<GroupHoliday, Long> {

    @Query("select gh from GroupHoliday gh where gh.groupId = :groupId")
    List<GroupHoliday> findListByGroupId(@Param("groupId") Long groupId);
}
