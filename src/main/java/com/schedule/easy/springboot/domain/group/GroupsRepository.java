package com.schedule.easy.springboot.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

    @Query("select g from Groups g where g.groupId in (select ug.groupId from UserGroup ug where ug.userId = :userId)")
    List<Groups> findListByUserId(@Param("userId") Long userId);
}
