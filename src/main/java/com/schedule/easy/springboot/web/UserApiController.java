package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.domain.user.User;
import com.schedule.easy.springboot.service.UserService;
import com.schedule.easy.springboot.web.dto.UserRequestDto;
import com.schedule.easy.springboot.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/user")
    public UserResponseDto update(@LoginUser SessionUser user, @RequestBody UserRequestDto requestDto) {
        requestDto.setUserId(user.getUserId());
        return userService.save(requestDto);
    }
}
