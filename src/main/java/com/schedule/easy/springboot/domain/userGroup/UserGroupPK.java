package com.schedule.easy.springboot.domain.userGroup;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserGroupPK implements Serializable {
    private Long userSeq;
    private Long groupSeq;
    private Long roleSeq;
}
