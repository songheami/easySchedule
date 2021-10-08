package com.schedule.easy.springboot.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g where g.seq in (select ug.groupSeq from UserGroup ug where ug.userSeq = :userSeq)")
    List<Group> findListByUserSeq(@Param("userSeq") Long userSeq);

    @Query("select count(g) from Group g where name = :name")
    int existsByName(@Param("name") String name);

    @Query("select g from Group g " +
            "left join UserGroup ug " +
            "on g.seq = ug.groupSeq " +
            "where g.name like concat('%',:name,'%') " +
            "and ug.roleSeq = :roleSeq " +
            "and not (ug.roleSeq = :roleSeq and ug.userSeq = :userSeq) ")
    List<Group> findListByName(@Param("name") String name, @Param("roleSeq") Long roleSeq, @Param("userSeq") Long userSeq);
}
