package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.UserGroupHolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserGroupHolidayController {

    private final UserGroupHolidayService userGroupHolidayService;

    @GetMapping("/userGroup/holiday")
    public String userGroupHoliday(@LoginUser SessionUser user, Model model) {
        model.addAttribute("userGroupHolidayList", userGroupHolidayService.findListByKey(user.getUserGroup().getUserId(), user.getUserGroup().getGroupId()));
        return "userGroup-holiday";
    }
}
