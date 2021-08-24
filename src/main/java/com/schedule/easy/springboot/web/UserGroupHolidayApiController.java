package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.GroupHolidayService;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import com.schedule.easy.springboot.service.UserGroupHolidayService;
import com.schedule.easy.springboot.web.dto.GroupHolidayRequestDto;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import com.schedule.easy.springboot.web.dto.UserGroupHolidayRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserGroupHolidayApiController {

    private final UserGroupHolidayService userGroupHolidayService;

    @PostMapping("/api/v1/userGroup/holiday")
    public Long makeUserGroupHoliday(@LoginUser SessionUser user, @RequestBody UserGroupHolidayRequestDto requestDto) {
        requestDto.setGroupId(user.getUserGroup().getGroupId());
        requestDto.setUserId(user.getUserGroup().getUserId());
        requestDto.setUseYn("Y");
        return userGroupHolidayService.save(user.getUserGroup().getGroupId(), requestDto);
    }
}
