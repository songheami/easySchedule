package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import com.schedule.easy.springboot.service.UserGroupOpertimeService;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import com.schedule.easy.springboot.web.dto.UserGroupOpertimeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserGroupOpertimeApiController {

    private final UserGroupOpertimeService userGroupOpertimeService;

    @PostMapping("/api/v1/userGroup/opertime")
    public Long makeGroupOpertime(@LoginUser SessionUser user, @RequestBody UserGroupOpertimeRequestDto requestDto) {
        requestDto.setUserId(user.getUserGroup().getUserId());
        requestDto.setGroupId(user.getUserGroup().getGroupId());
        requestDto.setUseYn("Y");
        return userGroupOpertimeService.save(requestDto);
    }
}
