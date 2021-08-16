package com.schedule.easy.springboot.domain.userGroup;

import com.schedule.easy.springboot.domain.BaseTimeEntity;
import com.schedule.easy.springboot.domain.group.Groups;
import com.schedule.easy.springboot.domain.role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_group")
@IdClass(UserGroupPK.class)
public class UserGroup extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = 2551178015035045050L;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "group_id")
    private Long groupId;

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "use_yn", length = 1, nullable = false)
    private String useYn;

    @ManyToOne
    @JoinColumn(name = "group_id", columnDefinition="LONG", insertable=false, updatable=false)
    private Groups groups;

    @ManyToOne
    @JoinColumn(name = "role_id", columnDefinition="LONG", insertable=false, updatable=false)
    private Role role;

    @Builder
    public UserGroup (Long userId, Long groupId, Long roleId, String useYn) {
        this.userId = userId;
        this.groupId = groupId;
        this.roleId = roleId;
        this.useYn = useYn;
    }

    public void update(String useYn) {
        this.useYn = useYn;
    }
}
