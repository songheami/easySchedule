package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.userGroupOpertime.UserGroupOpertime;
import com.schedule.easy.springboot.domain.userGroupOpertime.UserGroupOpertimeRepository;
import com.schedule.easy.springboot.web.dto.UserGroupOpertimeRequestDto;
import com.schedule.easy.springboot.web.dto.UserGroupOpertimeResponseDto;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import com.schedule.easy.springboot.web.dto.GroupOpertimeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserGroupOpertimeService {

    private final UserGroupOpertimeRepository userGroupOpertimeRepository;

    @Transactional(readOnly = true)
    public List<UserGroupOpertimeResponseDto> findListByKey(Long userId, Long groupId) {
        List<UserGroupOpertime> userGroupOpertimeList = userGroupOpertimeRepository.findListByKey(userId, groupId);
        List<UserGroupOpertimeResponseDto> userGroupOpertimeResponseDtoList = new ArrayList<UserGroupOpertimeResponseDto>();
        for(UserGroupOpertime userGroupOpertime: userGroupOpertimeList) { userGroupOpertimeResponseDtoList.add(new UserGroupOpertimeResponseDto(userGroupOpertime)); }
        return userGroupOpertimeResponseDtoList;
    }

    @Transactional
    public Long save(UserGroupOpertimeRequestDto requestDto) {
        return userGroupOpertimeRepository.save(requestDto.toEntity()).getOpertimeId();
    }
}
