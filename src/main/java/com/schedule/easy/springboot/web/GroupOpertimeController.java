package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class GroupOpertimeController {

    private final GroupOpertimeService groupOpertimeService;

    @GetMapping("/group/opertime")
    public String groupOpertime(@LoginUser SessionUser user, Model model) {
        // 그룹 운영 시간 설정 가능한 권한인지 확인
        if (user.getUserGroup().getRoleId().equals(1L)) {
            // 그룹 시간 조회
            model.addAttribute("groupOpertimeList", groupOpertimeService.findListByGroupId(user.getUserGroup().getGroupId()));
        }
        return "group-opertime";
    }
}
