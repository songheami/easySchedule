package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.groupHoliday.GroupHoliday;
import com.schedule.easy.springboot.domain.groupHoliday.GroupHolidayRepository;
import com.schedule.easy.springboot.web.dto.GroupHolidayRequestDto;
import com.schedule.easy.springboot.web.dto.GroupHolidayResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupHolidayService {

    private final GroupHolidayRepository groupHolidayRepository;

    @Transactional(readOnly = true)
    public List<GroupHolidayResponseDto> findListByGroupId(Long groupId) {
        List<GroupHoliday> groupHolidayList =  groupHolidayRepository.findListByGroupId(groupId);
        List<GroupHolidayResponseDto> groupResponseDtoList = new ArrayList<GroupHolidayResponseDto>();
        for(GroupHoliday groupHoliday: groupHolidayList) { groupResponseDtoList.add(new GroupHolidayResponseDto(groupHoliday)); }
        return groupResponseDtoList;
    }

    @Transactional
    public Long save(Long groupId, GroupHolidayRequestDto requestDto) {
        return groupHolidayRepository.save(requestDto.toEntity(groupId)).getGroupHolidayNum();
    }
}
