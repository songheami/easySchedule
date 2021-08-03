package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.group.Groups;
import com.schedule.easy.springboot.domain.group.GroupsRepository;
import com.schedule.easy.springboot.domain.posts.Posts;
import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.domain.userGroup.UserGroupRepository;
import com.schedule.easy.springboot.web.dto.PostsUpdateRequestDto;
import com.schedule.easy.springboot.web.dto.UserGroupRequestDto;
import com.schedule.easy.springboot.web.dto.UserGroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final GroupsRepository groupsRepository;

    @Transactional(readOnly = true)
    public List<UserGroupResponseDto> findByUserId(Long userId) {
        List<UserGroup> userGroupList =  userGroupRepository.findListByUserId(userId);
        List<UserGroupResponseDto> userGroupResponseDtoList = new ArrayList<>();
        for (UserGroup userGroup : userGroupList) {
            userGroupResponseDtoList.add(new UserGroupResponseDto(userGroup));
        }
        return userGroupResponseDtoList;
    }

    @Transactional(readOnly = true)
    public UserGroup findByKey(Long userId, Long groupId, Long roleId) {
        return userGroupRepository.findOneByKey(userId, groupId, roleId);
    }

    @Transactional(readOnly = true)
    public UserGroupResponseDto save(Long userId, Long groupId) {
        Groups groups = groupsRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("선택한 그룹이 존재하지 않습니다. groupId=" + groupId));

        UserGroupRequestDto requestDto = new UserGroupRequestDto(new UserGroup(userId, groupId, 2L, "Y"));
        UserGroup userGroup = requestDto.toEntity();
        userGroupRepository.save(userGroup);
        return new UserGroupResponseDto(userGroup);
    }
}
