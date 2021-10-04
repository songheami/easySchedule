package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import com.schedule.easy.springboot.service.ScheduleService;
import com.schedule.easy.springboot.service.UserGroupService;
import com.schedule.easy.springboot.web.dto.ScheduleRequestDto;
import com.schedule.easy.springboot.web.dto.ScheduleResponseDto;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScheduleApiController {

    private final ScheduleService scheduleService;

    @GetMapping("/api/v1/schedule")
    public List<ScheduleResponseDto> findList(@RequestParam(value="staffSeqList[]") List<Long> staffSeqList,
                                              @RequestParam(value="searchStartTime") String searchStartTime,
                                              @RequestParam(value="searchEndTime") String searchEndTime) {
        return scheduleService.findListBySearchTime(staffSeqList, searchStartTime, searchEndTime);
    }

    @PostMapping("/api/v1/schedule")
    public Long save(@LoginUser SessionUser user, @RequestBody ScheduleRequestDto requestDto) {
        requestDto.setMembSeq(user.getSeq());
        return scheduleService.save(requestDto);
    }
}
