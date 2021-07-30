package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.group.Groups;
import com.schedule.easy.springboot.domain.group.GroupsRepository;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.domain.userGroup.UserGroupRepository;
import com.schedule.easy.springboot.web.dto.GroupOpertimeRequestDto;
import com.schedule.easy.springboot.web.dto.GroupSaveRequestDto;
import com.schedule.easy.springboot.web.dto.GroupsResponseDto;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupService {
    private final GroupsRepository groupRepository;

    private final UserGroupRepository userGroupRepository;

    @Transactional(readOnly = true)
    public List<GroupsResponseDto> findGroupsByUserId(Long userId) {
        List<Groups> groupsList = groupRepository.findListByUserId(userId);
        List<GroupsResponseDto> groupsResponseDtoList = new ArrayList<GroupsResponseDto>();
        for(Groups groups: groupsList) { groupsResponseDtoList.add(new GroupsResponseDto(groups)); }
        return groupsResponseDtoList;
    }

    @Transactional
    public UserGroupResponseDto save(Long userId, GroupSaveRequestDto requestDto) {
        Long groupId = groupRepository.save(requestDto.toEntity()).getGroupId();
        UserGroup userGroup = new UserGroup(userId, groupId, 1L, "Y");
        userGroupRepository.save(userGroup);
        return new UserGroupResponseDto(userGroup);
    }

}
