package com.schedule.easy.springboot.domain.opertime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpertimeRepository extends JpaRepository<Opertime, Long> {

    @Query("select op from Opertime op where op.userSeq = :userSeq and op.groupSeq = :groupSeq order by dayCode, startTime")
    List<Opertime> findListByKey(@Param("userSeq") Long userSeq, @Param("groupSeq") Long groupSeq);
}
