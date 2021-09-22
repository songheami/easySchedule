package com.schedule.easy.springboot.web.dto;

import com.schedule.easy.springboot.domain.user.User;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String picture;

    public UserRequestDto(User entity) {
        this.userId = entity.getUserId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.picture = entity.getPicture();
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .phone(phone)
                .picture(picture)
                .build();
    }
}
