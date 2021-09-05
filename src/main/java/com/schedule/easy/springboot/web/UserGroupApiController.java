package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.service.GroupService;
import com.schedule.easy.springboot.service.UserGroupService;
import com.schedule.easy.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserGroupApiController {

    private final UserGroupService userGroupService;

    @PostMapping("/api/v1/userGroup")
    public void insertUserGroup(@RequestBody UserGroupRequestDto requestDto) {
        userGroupService.save(requestDto);
    }
}
