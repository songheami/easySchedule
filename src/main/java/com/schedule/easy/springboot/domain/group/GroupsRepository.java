package com.schedule.easy.springboot.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

    @Query("select g from Groups g where g.groupId in (select ug.groupId from UserGroup ug where ug.userId = :userId)")
    List<Groups> findListByUserId(@Param("userId") Long userId);

    @Query("select count(g) from Groups g where name = :name")
    int existsByName(@Param("name") String name);

    @Query("select g from Groups g left join UserGroup ug " +
            "on g.groupId = ug.groupId where g.name like concat('%',:name,'%')" +
            "and (ug.roleId != :roleId or ug.userId != :userId)")
    List<Groups> findListByName(@Param("name") String name, @Param("roleId") Long roleId, @Param("userId") Long userId);
}
