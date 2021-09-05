package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.OpertimeService;
import com.schedule.easy.springboot.web.dto.OpertimeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OpertimeApiController {

    private final OpertimeService opertimeService;

    @PostMapping("/api/v1/opertime")
    public void save(@LoginUser SessionUser user, @RequestBody List<OpertimeRequestDto> requestDto) {
        for (OpertimeRequestDto opertimeRequestDto : requestDto) {
            opertimeRequestDto.setUserId(user.getUserGroup().getUserId());
            opertimeRequestDto.setGroupId(user.getUserGroup().getGroupId());
            opertimeRequestDto.setUseYn("Y");
            opertimeService.save(opertimeRequestDto);
        }
    }
}
