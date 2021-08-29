package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import com.schedule.easy.springboot.service.GroupService;
import com.schedule.easy.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GroupApiController {

    private final GroupService groupService;

    @PostMapping("/api/v1/group")
    public UserGroupResponseDto save(@LoginUser SessionUser user, @RequestBody GroupSaveRequestDto requestDto) {
        return groupService.save(user.getUserId(), requestDto);
    }

    @GetMapping("/api/v1/group/{name}")
    public int existsByName(@PathVariable String name) {
        return groupService.existsByName(name);
    }

    @GetMapping("/api/v1/group")
    public List<GroupsResponseDto> findListByName(@LoginUser SessionUser user,
                                                  @RequestParam(value="role") String role,
                                                  @RequestParam(value="name") String name) {
        return groupService.findListByName(name, role.equals("staff")?2L:3L, user.getUserId());
    }
}
