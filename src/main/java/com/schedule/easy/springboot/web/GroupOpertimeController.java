package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.service.GroupOpertimeService;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class GroupOpertimeController {

    private final GroupOpertimeService groupOpertimeService;

    @GetMapping("/group/opertime")
    public String groupOpertime(@LoginUser SessionUser user, Model model) {
        UserGroup userGroup = user.getUserGroup();
        if (userGroup == null) return "index";

        // 그룹 운영 시간 설정 가능한 권한인지 확인
        if (userGroup.getRoleId().equals(1L)) {
            // 그룹 시간 조회
            model.addAttribute("groupOpertimeList", groupOpertimeService.findListByGroupId(user.getUserGroup().getGroupId()));
        }

        return "group-opertime";
    }
}
