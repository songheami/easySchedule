package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.service.GroupService;
import com.schedule.easy.springboot.service.UserGroupService;
import com.schedule.easy.springboot.web.dto.GroupSaveRequestDto;
import com.schedule.easy.springboot.web.dto.GroupsResponseDto;
import com.schedule.easy.springboot.web.dto.UserGroupRequestDto;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserGroupApiController {

    private final UserGroupService userGroupService;

    @PostMapping("/api/v1/userGroup/{groupId}")
    public UserGroupResponseDto insertUserGroup(@LoginUser SessionUser user,
                                                @PathVariable Long groupId) {
        return userGroupService.save(user.getUserId(), groupId);
    }
}
