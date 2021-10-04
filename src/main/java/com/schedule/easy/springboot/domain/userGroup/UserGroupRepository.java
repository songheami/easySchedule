package com.schedule.easy.springboot.domain.userGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("SELECT ug, u FROM UserGroup ug " +
            "INNER JOIN User u ON ug.userSeq = u.seq " +
            "WHERE ug.roleSeq in (1,2) AND ug.groupSeq = :groupSeq")
    public List<UserGroup> findStaffListWithGroupSeq(@Param("groupSeq") Long groupSeq);

    @Query("SELECT ug, g, r FROM UserGroup ug " +
            "INNER JOIN Group g ON ug.groupSeq = g.seq " +
            "INNER JOIN Role r ON ug.roleSeq = r.seq " +
            "WHERE ug.userSeq = :userSeq")
    public List<UserGroup> findListByUserSeq(@Param("userSeq") Long userSeq);

    @Query("SELECT ug, g FROM UserGroup ug INNER JOIN Group g ON ug.groupSeq = g.seq " +
            "WHERE ug.userSeq = :userSeq and ug.groupSeq = :groupSeq and ug.roleSeq = :roleSeq")
    public UserGroup findOneByKey(@Param("userSeq") Long userSeq,
                                  @Param("groupSeq") Long groupSeq,
                                  @Param("roleSeq") Long roleSeq);
}
