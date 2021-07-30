package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import com.schedule.easy.springboot.service.GroupService;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import com.schedule.easy.springboot.web.dto.GroupOpertimeResponseDto;
import com.schedule.easy.springboot.web.dto.GroupSaveRequestDto;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GroupApiController {

    private final GroupService groupService;
    private final GroupOpertimeService groupOpertimeService;

    @PostMapping("/api/v1/group")
    public UserGroupResponseDto makeGroup(@LoginUser SessionUser user, @RequestBody GroupSaveRequestDto requestDto) {
        return groupService.save(user.getUserId(), requestDto);
    }
}
