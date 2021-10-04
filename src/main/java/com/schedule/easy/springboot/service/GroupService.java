package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.group.Group;
import com.schedule.easy.springboot.domain.group.GroupRepository;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.domain.userGroup.UserGroupRepository;
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
    private final GroupRepository groupRepository;

    private final UserGroupRepository userGroupRepository;

    @Transactional(readOnly = true)
    public List<GroupsResponseDto> findListByUserSeq(Long userSeq) {
        List<Group> groupList = groupRepository.findListByUserSeq(userSeq);
        List<GroupsResponseDto> groupsResponseDtoList = new ArrayList<GroupsResponseDto>();
        for(Group group : groupList) { groupsResponseDtoList.add(new GroupsResponseDto(group)); }
        return groupsResponseDtoList;
    }

    @Transactional
    public UserGroupResponseDto save(Long userSeq, GroupSaveRequestDto requestDto) {
        Long groupSeq = groupRepository.save(requestDto.toEntity()).getSeq();
        UserGroup userGroup = new UserGroup(userSeq, groupSeq, 1L, "Y");
        userGroupRepository.save(userGroup);
        return new UserGroupResponseDto(userGroup);
    }

    @Transactional
    public int existsByName(String name) {
        return groupRepository.existsByName(name);
    }

    @Transactional
    public List<GroupsResponseDto> findListByName(String name, Long roleSeq, Long userSeq) {
        List<Group> groupList = groupRepository.findListByName(name, roleSeq, userSeq);
        List<GroupsResponseDto> groupsResponseDtoList = new ArrayList<GroupsResponseDto>();
        for(Group group : groupList) { groupsResponseDtoList.add(new GroupsResponseDto(group)); }
        return groupsResponseDtoList;
    }
}
