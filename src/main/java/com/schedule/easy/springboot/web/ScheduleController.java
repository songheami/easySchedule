package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.service.OpertimeService;
import com.schedule.easy.springboot.service.UserGroupService;
import com.schedule.easy.springboot.web.dto.OpertimeResponseDto;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ScheduleController {

    private final HttpSession httpSession;

    private final UserGroupService userGroupService;
    private final OpertimeService opertimeService;

    @GetMapping("/schedule")
    public String schedule(@LoginUser SessionUser user,
                             @RequestParam(value="groupId") Long groupId,
                             @RequestParam(value="roleId") Long roleId,
                             Model model) {
        UserGroup userGroup = userGroupService.findByKey(user.getUserId(), groupId, roleId);
        if (userGroup == null) return "index";

        // 현재 사용자의 그룹 권한 변경
        user.sessionUserGroup(userGroup);
        httpSession.setAttribute("user", user);

        boolean groupOwnerYn = userGroup.getRoleId().equals(1L);
        if (groupOwnerYn) {
            model.addAttribute("groupOwner", true);
            List<UserGroupResponseDto> staffList = userGroupService.findStaffListWithGroupId(userGroup.getGroupId());
            model.addAttribute("staffList", staffList);
            List<OpertimeResponseDto> opertimeResponseDtoList = new ArrayList<>();
            for (UserGroupResponseDto staff : staffList) {
                List<OpertimeResponseDto> tmp = opertimeService.findListByKey(groupId, staff.getUserId());
                tmp.forEach(opertimeResponseDto -> opertimeResponseDtoList.add(opertimeResponseDto));
            }
            model.addAttribute("opertimeList", opertimeResponseDtoList);
        } else {
            model.addAttribute("groupOwner", false);
            model.addAttribute("staffList", userGroup);
            List<OpertimeResponseDto> opertimeResponseDtoList = opertimeService.findListByKey(groupId, userGroup.getUserId());
            opertimeResponseDtoList.forEach(opertimeResponseDto -> opertimeResponseDtoList.add(opertimeResponseDto));
            model.addAttribute("opertimeList", opertimeResponseDtoList);
        }

        return "schedule";
    }
}
