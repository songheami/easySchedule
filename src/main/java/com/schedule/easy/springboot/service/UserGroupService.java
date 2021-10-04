package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.group.Group;
import com.schedule.easy.springboot.domain.group.GroupRepository;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.domain.userGroup.UserGroupRepository;
import com.schedule.easy.springboot.web.dto.UserGroupRequestDto;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final GroupRepository groupsRepository;

    @Transactional(readOnly = true)
    public List<UserGroupResponseDto> findListByUserSeq(Long userSeq) {
        List<UserGroup> userGroupList =  userGroupRepository.findListByUserSeq(userSeq);
        List<UserGroupResponseDto> userGroupResponseDtoList = new ArrayList<>();
        for (UserGroup userGroup : userGroupList) {
            userGroupResponseDtoList.add(new UserGroupResponseDto(userGroup));
        }
        return userGroupResponseDtoList;
    }

    @Transactional(readOnly = true)
    public UserGroup findByKey(Long userSeq, Long groupSeq, Long roleSeq) {
        return userGroupRepository.findOneByKey(userSeq, groupSeq, roleSeq);
    }

    @Transactional(readOnly = true)
    public UserGroupResponseDto save(UserGroupRequestDto requestDto) {
        Group group = groupsRepository.findById(requestDto.getGroupSeq())
                .orElseThrow(() -> new IllegalArgumentException("선택한 그룹이 존재하지 않습니다. groupSeq=" + requestDto.getGroupSeq()));
        return new UserGroupResponseDto(userGroupRepository.save(requestDto.toEntity()));
    }

    @Transactional(readOnly = true)
    public List<UserGroupResponseDto> findStaffListWithGroupSeq(Long groupSeq) {
        List<UserGroup> userGroupList = userGroupRepository.findStaffListWithGroupSeq(groupSeq);
        List<UserGroupResponseDto> userGroupResponseDtoList = new ArrayList<UserGroupResponseDto>();
        for (UserGroup userGroup : userGroupList) {
            userGroupResponseDtoList.add(new UserGroupResponseDto(userGroup));
        }
        return userGroupResponseDtoList;
    }
}
