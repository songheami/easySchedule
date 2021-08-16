package com.schedule.easy.springboot.domain.role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 825785831811652295L;

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "root_role_id")
    private Long rootRoleId;

    @Column(name = "use_yn", length = 1, nullable = false)
    private String useYn;

    @Builder
    public Role(Long roleId, String name, Long rootRoleId, String useYn) {
        this.roleId = roleId;
        this.rootRoleId = rootRoleId;
        this.name = name;
        this.useYn = useYn;
    }
}
