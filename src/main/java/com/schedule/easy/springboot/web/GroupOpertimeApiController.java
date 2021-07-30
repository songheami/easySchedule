package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class GroupOpertimeApiController {

    private final GroupOpertimeService groupOpertimeService;

    @PostMapping("/api/v1/schedule")
    public Long makeGroupSchdule(@LoginUser SessionUser user, @RequestBody GroupOpertimeRequestDto requestDto) {
        return groupOpertimeService.save(user.getUserGroup().getGroupId(), requestDto);
    }
}
