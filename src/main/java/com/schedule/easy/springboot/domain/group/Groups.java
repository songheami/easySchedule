package com.schedule.easy.springboot.domain.group;

import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Groups implements Serializable {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "use_yn", length = 1, nullable = false)
    private String useYn;

    @Builder
    public Groups(Long groupId, String name, String useYn) {
        this.groupId = groupId;
        this.name = name;
        this.useYn = useYn;
    }

    public void update(String useYn) {
        this.useYn = useYn;
    }
}
