package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.service.GroupHolidayService;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class GroupHolidayController {

    private final GroupHolidayService groupHolidayService;

    @GetMapping("/group/holiday")
    public String groupHoliday(@LoginUser SessionUser user, Model model) {
        // 그룹 휴일 설정 가능한 권한인지 확인
        if (user.getUserGroup().getRoleId().equals(1L)) {
            // 그룹 휴일 조회
            model.addAttribute("groupHolidayList", groupHolidayService.findListByGroupId(user.getUserGroup().getGroupId()));
        }
        return "group-holiday";
    }
}
