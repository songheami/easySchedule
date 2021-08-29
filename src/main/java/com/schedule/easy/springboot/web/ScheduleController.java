package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ScheduleController {

    private final HttpSession httpSession;

    private final UserGroupService userGroupService;

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
            model.addAttribute("staffList", userGroupService.findStaffListWithGroupId(userGroup.getGroupId()));
        } else {
            model.addAttribute("groupOwner", false);
            model.addAttribute("staffList", userGroup);
        }

        return "schedule";
    }
}
