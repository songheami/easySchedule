package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long seq;
    private String name;
    private String email;
    private String phone;
    private String picture;

    public UserResponseDto(User entity) {
        this.seq = entity.getSeq();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.picture = entity.getPicture();
    }
}
