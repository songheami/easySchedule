package com.schedule.easy.springboot.domain.groupOpertime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupOpertimeRepository extends JpaRepository<GroupOpertime, Long> {

    @Query("select go from GroupOpertime go where go.groupId = :groupId")
    List<GroupOpertime> findListByGroupId(@Param("groupId") Long groupId);
}
