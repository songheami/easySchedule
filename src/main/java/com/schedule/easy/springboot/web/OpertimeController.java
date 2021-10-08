package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.OpertimeService;
import com.schedule.easy.springboot.web.dto.OpertimeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class OpertimeController {

    private final OpertimeService opertimeService;

    @GetMapping("/opertime")
    public String findListByKey(@LoginUser SessionUser user, Model model) {
        // 근무시간 조회
        List<OpertimeResponseDto> monOpertimeList = new ArrayList<OpertimeResponseDto>();
        List<OpertimeResponseDto> tueOpertimeList = new ArrayList<OpertimeResponseDto>();
        List<OpertimeResponseDto> wedOpertimeList = new ArrayList<OpertimeResponseDto>();
        List<OpertimeResponseDto> thuOpertimeList = new ArrayList<OpertimeResponseDto>();
        List<OpertimeResponseDto> friOpertimeList = new ArrayList<OpertimeResponseDto>();
        List<OpertimeResponseDto> satOpertimeList = new ArrayList<OpertimeResponseDto>();
        List<OpertimeResponseDto> sunOpertimeList = new ArrayList<OpertimeResponseDto>();
        // 요일별 구분
        opertimeService.findListByKey(user.getUserGroup().getUserSeq(), user.getUserGroup().getGroupSeq()).forEach(opertimeResponseDto -> {
            switch (opertimeResponseDto.getDayCode().toString()) {
                case "0":
                    sunOpertimeList.add(opertimeResponseDto);
                    break;
                case "1":
                    monOpertimeList.add(opertimeResponseDto);
                    break;
                case "2":
                    tueOpertimeList.add(opertimeResponseDto);
                    break;
                case "3":
                    wedOpertimeList.add(opertimeResponseDto);
                    break;
                case "4":
                    thuOpertimeList.add(opertimeResponseDto);
                    break;
                case "5":
                    friOpertimeList.add(opertimeResponseDto);
                    break;
                case "6":
                    satOpertimeList.add(opertimeResponseDto);
                    break;
            }
        });
        model.addAttribute("monOpertimeList", monOpertimeList);
        model.addAttribute("tueOpertimeList", tueOpertimeList);
        model.addAttribute("wedOpertimeList", wedOpertimeList);
        model.addAttribute("thuOpertimeList", thuOpertimeList);
        model.addAttribute("friOpertimeList", friOpertimeList);
        model.addAttribute("satOpertimeList", satOpertimeList);
        model.addAttribute("sunOpertimeList", sunOpertimeList);
        return "opertime";
    }
}
