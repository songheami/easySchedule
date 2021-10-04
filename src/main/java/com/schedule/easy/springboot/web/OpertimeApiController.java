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
                                                   @RequestParam(value="staffSeqList[]") List<Long> staffSeqList) {
        List<OpertimeResponseDto> opertimeResponseDtoList = new ArrayList<>();
        for (Long staffSeq : staffSeqList) {
            opertimeResponseDtoList.addAll(opertimeService.findListByKey(staffSeq, user.getUserGroup().getGroupSeq()));
        }
        return opertimeResponseDtoList;
    }

    @PostMapping("/api/v1/opertime")
    public void save(@LoginUser SessionUser user, @RequestBody List<OpertimeRequestDto> requestDto) {
        for (OpertimeRequestDto opertimeRequestDto : requestDto) {
            opertimeRequestDto.setUserSeq(user.getUserGroup().getUserSeq());
            opertimeRequestDto.setGroupSeq(user.getUserGroup().getGroupSeq());
            opertimeRequestDto.setUseYn("Y");
            opertimeService.save(opertimeRequestDto);
        }
    }
}
