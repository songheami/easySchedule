package com.schedule.easy.springboot.domain.opertime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpertimeRepository extends JpaRepository<Opertime, Long> {

    @Query("select op from Opertime op where op.userId = :userId and op.groupId = :groupId")
    List<Opertime> findListByKey(@Param("userId") Long userId, @Param("groupId") Long groupId);
}
