package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.UserGroupService;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserGroupService userGroupService;

    @GetMapping("/")
    public String index(@LoginUser SessionUser user, Model model) {
        if (user != null) {
            List<UserGroupResponseDto> userGroupResponseDtoList = userGroupService.findByUserId(user.getUserId());
            model.addAttribute("userGroups", userGroupResponseDtoList);
            return "home";
        }
        return "index";
    }
}
