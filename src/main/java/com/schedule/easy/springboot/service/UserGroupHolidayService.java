package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.groupHoliday.GroupHoliday;
import com.schedule.easy.springboot.domain.groupHoliday.GroupHolidayRepository;
import com.schedule.easy.springboot.domain.userGroupHoliday.UserGroupHoliday;
import com.schedule.easy.springboot.domain.userGroupHoliday.UserGroupHolidayRepository;
import com.schedule.easy.springboot.web.dto.GroupHolidayRequestDto;
import com.schedule.easy.springboot.web.dto.GroupHolidayResponseDto;
import com.schedule.easy.springboot.web.dto.UserGroupHolidayRequestDto;
import com.schedule.easy.springboot.web.dto.UserGroupHolidayResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserGroupHolidayService {

    private final UserGroupHolidayRepository userGroupHolidayRepository;

    @Transactional(readOnly = true)
    public List<UserGroupHolidayResponseDto> findListByKey(Long userId, Long groupId) {
        List<UserGroupHoliday> userGroupHolidayList =  userGroupHolidayRepository.findListByKey(userId, groupId);
        List<UserGroupHolidayResponseDto> userGroupResponseDtoList = new ArrayList<UserGroupHolidayResponseDto>();
        for(UserGroupHoliday userGroupHoliday: userGroupHolidayList) { userGroupResponseDtoList.add(new UserGroupHolidayResponseDto(userGroupHoliday)); }
        return userGroupResponseDtoList;
    }

    @Transactional
    public Long save(Long groupId, UserGroupHolidayRequestDto requestDto) {
        return userGroupHolidayRepository.save(requestDto.toEntity()).getUserGroupHolidayNum();
    }
}
