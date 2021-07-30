package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.userGroup.UserGroup;
import com.schedule.easy.springboot.domain.userGroup.UserGroupRepository;
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
}
