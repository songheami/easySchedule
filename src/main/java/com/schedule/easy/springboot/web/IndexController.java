package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.UserGroupService;
import com.schedule.easy.springboot.service.UserService;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import com.schedule.easy.springboot.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserService userService;
    private final UserGroupService userGroupService;

    @GetMapping("/")
    public String index(@LoginUser SessionUser user, Model model) {
        if (user != null) {
            model.addAttribute("userGroups", userGroupService.findListByUserSeq(user.getSeq()));
            return "home";
        }
        return "index";
    }

    @GetMapping("/mypage")
    public String mypage(@LoginUser SessionUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", userService.findById(user.getSeq()));
            return "mypage";
        }
        return "index";
    }
}
