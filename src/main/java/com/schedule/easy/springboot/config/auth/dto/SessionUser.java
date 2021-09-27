package com.schedule.easy.springboot.config.auth.dto;

import com.schedule.easy.springboot.domain.user.User;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long seq;
    private String name;
    private String email;
    private String picture;
    private UserGroup userGroup;

    public SessionUser(User user) {
        this.seq = user.getSeq();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

    public void sessionUserGroup (UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
