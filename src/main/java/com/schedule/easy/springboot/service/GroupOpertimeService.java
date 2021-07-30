package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.groupOpertime.GroupOpertime;
import com.schedule.easy.springboot.domain.groupOpertime.GroupOpertimeRepository;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import com.schedule.easy.springboot.web.dto.GroupOpertimeResponseDto;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupOpertimeService {

    private final GroupOpertimeRepository groupOpertimeRepository;

    @Transactional(readOnly = true)
    public List<GroupOpertimeResponseDto> findListByGroupId(Long groupId) {
        List<GroupOpertime> groupOpertimeList = groupOpertimeRepository.findListByGroupId(groupId);
        List<GroupOpertimeResponseDto> groupResponseDtoList = new ArrayList<GroupOpertimeResponseDto>();
        for(GroupOpertime groupOpertime: groupOpertimeList) { groupResponseDtoList.add(new GroupOpertimeResponseDto(groupOpertime)); }
        return groupResponseDtoList;
    }

    @Transactional
    public Long save(Long userId, GroupOpertimeRequestDto requestDto) {
        return groupOpertimeRepository.save(requestDto.toEntity()).getGroupId();
    }
}
