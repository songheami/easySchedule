package com.schedule.easy.springboot.domain.userGroupOpertime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupOpertimeRepository extends JpaRepository<UserGroupOpertime, Long> {

    @Query("select ug from UserGroupOpertime ug where ug.userId = :userId and ug.groupId = :groupId")
    List<UserGroupOpertime> findListByKey(@Param("userId") Long userId, @Param("groupId") Long groupId);
}
