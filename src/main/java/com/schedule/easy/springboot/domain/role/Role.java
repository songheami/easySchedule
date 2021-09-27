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
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "root_seq")
    private Long rootSeq;

    @Column(name = "use_yn", length = 1, nullable = false)
    private String useYn;

    @Builder
    public Role(Long seq, String name, Long rootSeq, String useYn) {
        this.seq = seq;
        this.rootSeq = rootSeq;
        this.name = name;
        this.useYn = useYn;
    }
}
