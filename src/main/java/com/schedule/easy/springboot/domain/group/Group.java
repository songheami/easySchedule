package com.schedule.easy.springboot.domain.group;

import com.schedule.easy.springboot.domain.user.User;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_group")
public class Group implements Serializable {

    private static final long serialVersionUID = 2561430858992520080L;

    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "use_yn", length = 1, nullable = false)
    private String useYn;

    @OneToMany
    @JoinColumn(name="group_seq")
    private Collection<UserGroup> userGroup;

    @Builder
    public Group(Long seq, String name, String useYn) {
        this.seq = seq;
        this.name = name;
        this.useYn = useYn;
    }

    public void update(String useYn) {
        this.useYn = useYn;
    }
}
