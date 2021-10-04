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
                             @RequestParam(value="groupSeq") Long groupSeq,
                             @RequestParam(value="roleSeq") Long roleSeq,
                             Model model) {
        UserGroup userGroup = userGroupService.findByKey(user.getSeq(), groupSeq, roleSeq);
        if (userGroup == null) return "index";

        // 현재 사용자의 그룹 권한 변경
        user.sessionUserGroup(userGroup);
        httpSession.setAttribute("user", user);

        boolean groupOwnerYn = userGroup.getRoleSeq().equals(1L);
        if (groupOwnerYn) {
            model.addAttribute("groupOwner", true);
            model.addAttribute("staffList", userGroupService.findStaffListWithGroupSeq(userGroup.getGroupSeq()));
        } else {
            model.addAttribute("groupOwner", false);
            model.addAttribute("staffList", userGroup);
        }

        return "schedule";
    }
}
