package com.schedule.easy.springboot.domain.userGroupHoliday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupHolidayRepository extends JpaRepository<UserGroupHoliday, Long> {

    @Query("select ugh from UserGroupHoliday ugh where ugh.userId = :userId and ugh.groupId = :groupId")
    List<UserGroupHoliday> findListByKey(@Param("userId") Long userId, @Param("groupId") Long groupId);
}
