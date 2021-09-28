package com.schedule.easy.springboot.domain.userGroup;

import com.schedule.easy.springboot.domain.BaseTimeEntity;
import com.schedule.easy.springboot.domain.group.Group;
import com.schedule.easy.springboot.domain.role.Role;
import com.schedule.easy.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_user_group")
@IdClass(UserGroupPK.class)
public class UserGroup extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = 2551178015035045050L;

    @Id
    @Column(name = "user_seq")
    private Long userSeq;

    @Id
    @Column(name = "group_seq")
    private Long groupSeq;

    @Id
    @Column(name = "role_seq")
    private Long roleSeq;

    @Column(name = "use_yn", length = 1, nullable = false)
    private String useYn;

    @ManyToOne
    @JoinColumn(name = "user_seq", columnDefinition="LONG", insertable=false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_seq", columnDefinition="LONG", insertable=false, updatable=false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "role_seq", columnDefinition="LONG", insertable=false, updatable=false)
    private Role role;

    @Builder
    public UserGroup (Long userSeq, Long groupSeq, Long roleSeq, String useYn) {
        this.userSeq = userSeq;
        this.groupSeq = groupSeq;
        this.roleSeq = roleSeq;
        this.useYn = useYn;
    }

    public void update(String useYn) {
        this.useYn = useYn;
    }
}
