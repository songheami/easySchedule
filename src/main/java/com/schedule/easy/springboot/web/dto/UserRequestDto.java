package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.user.User;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    private Long seq;
    private String name;
    private String email;
    private String phone;
    private String picture;

    public UserRequestDto(User entity) {
        this.seq = entity.getSeq();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.picture = entity.getPicture();
    }

    public User toEntity() {
        return User.builder()
                .seq(seq)
                .name(name)
                .email(email)
                .phone(phone)
                .picture(picture)
                .build();
    }
}
