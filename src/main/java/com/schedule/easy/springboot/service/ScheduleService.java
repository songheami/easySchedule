package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.schedule.Schedule;
import com.schedule.easy.springboot.domain.schedule.ScheduleRepository;
import com.schedule.easy.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findListBySearchTime(List<Long> staffSeqList, String searchStartTime, String searchEndTime) {
        List<Schedule> scheduleList = scheduleRepository.findListBySearchTime(staffSeqList, searchStartTime, searchEndTime);
        List<ScheduleResponseDto> scheduleResponseDtoList = new ArrayList<ScheduleResponseDto>();
        for(Schedule schedule : scheduleList) { scheduleResponseDtoList.add(new ScheduleResponseDto(schedule)); }
        return scheduleResponseDtoList;
    }

    @Transactional
    public Long save(@RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(requestDto.getSeq())
                .map(entity -> entity.update(requestDto.getStatCode(), requestDto.getStaffSeq(), requestDto.getTitle(),
                        requestDto.getStartTime(), requestDto.getEndTime()))
                .orElse(requestDto.toEntity());
        return scheduleRepository.save(requestDto.toEntity()).getSeq();
    }
}
