package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.domain.opertime.Opertime;
import com.schedule.easy.springboot.service.OpertimeService;
import com.schedule.easy.springboot.web.dto.OpertimeRequestDto;
import com.schedule.easy.springboot.web.dto.OpertimeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OpertimeApiController {

    private final OpertimeService opertimeService;

    @GetMapping("/api/v1/opertime")
    public List<OpertimeResponseDto> findListByKey(@LoginUser SessionUser user,
                                                   @RequestParam(value="staffIdList[]") List<Long> staffIdList) {
        List<OpertimeResponseDto> opertimeResponseDtoList = new ArrayList<>();
        for (Long staffId : staffIdList) {
            opertimeResponseDtoList.addAll(opertimeService.findListByKey(staffId, user.getUserGroup().getGroupId()));
        }
        return opertimeResponseDtoList;
    }

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
