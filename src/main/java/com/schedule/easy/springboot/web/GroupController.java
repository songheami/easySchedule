package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.GroupService;
import com.schedule.easy.springboot.web.dto.GroupSaveRequestDto;
import com.schedule.easy.springboot.web.dto.GroupsResponseDto;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/group")
    public String group() {
        return "group";
    }

    @GetMapping("/group/create")
    public String createGroup() {
        return "group-create";
    }

    @GetMapping("/group/join")
    public String joinGroup() {
        return "group-join";
    }

    @GetMapping("/group/join/{role}")
    public String roleJoinGroup(@PathVariable String role) {
        return role.equals("staff")?"group-join-staff":"group-join-member";
    }
}
