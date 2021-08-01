package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GroupOpertimeApiController {

    private final GroupOpertimeService groupOpertimeService;

    @PostMapping("/api/v1/opertime")
    public Long makeGroupOpertime(@LoginUser SessionUser user, @RequestBody GroupOpertimeRequestDto requestDto) {
        return groupOpertimeService.save(user.getUserGroup().getGroupId(), requestDto);
    }
}
