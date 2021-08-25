package com.schedule.easy.springboot.domain.userGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("SELECT ug, u FROM UserGroup ug " +
            "INNER JOIN User u ON ug.userId = u.userId " +
            "WHERE ug.role in (1,2) AND ug.groupId = :groupId")
    public List<UserGroup> findStaffListWithGroupId(@Param("groupId") Long groupId);

    @Query("SELECT ug, g, r FROM UserGroup ug " +
            "INNER JOIN Groups g ON ug.groupId = g.groupId " +
            "INNER JOIN Role r ON ug.roleId = r.roleId WHERE ug.userId = :userId")
    public List<UserGroup> findListByUserId(@Param("userId") Long userId);

    @Query("SELECT ug, g FROM UserGroup ug INNER JOIN Groups g ON ug.groupId = g.groupId " +
            "WHERE ug.userId = :userId and ug.groupId = :groupId and ug.roleId = :roleId")
    public UserGroup findOneByKey(@Param("userId") Long userId,
                                  @Param("groupId") Long groupId,
                                  @Param("roleId") Long roleId);
}
