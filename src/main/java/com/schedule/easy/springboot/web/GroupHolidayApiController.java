package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.GroupHolidayService;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import com.schedule.easy.springboot.web.dto.GroupHolidayRequestDto;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GroupHolidayApiController {

    private final GroupHolidayService groupHolidayService;

    @PostMapping("/api/v1/holiday")
    public Long makeGroupHoliday(@LoginUser SessionUser user, @RequestBody GroupHolidayRequestDto requestDto) {
        return groupHolidayService.save(user.getUserGroup().getGroupId(), requestDto);
    }
}
