package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.user.User;
import com.schedule.easy.springboot.domain.user.UserRepository;
import com.schedule.easy.springboot.web.dto.UserRequestDto;
import com.schedule.easy.springboot.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("현재 로그인된 계정 정보가 없습니다." + userId));
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto save(UserRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .map(entity -> entity.update(requestDto.getName(), requestDto.getEmail(), requestDto.getPhone()))
                .orElse(requestDto.toEntity());
        return new UserResponseDto(user);
    }
}
