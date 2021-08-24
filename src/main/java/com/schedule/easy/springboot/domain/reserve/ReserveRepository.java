package com.schedule.easy.springboot.domain.reserve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Reserve> findAllDesc();
}
