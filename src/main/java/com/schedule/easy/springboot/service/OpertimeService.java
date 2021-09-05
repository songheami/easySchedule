package com.schedule.easy.springboot.service;

import com.schedule.easy.springboot.domain.opertime.Opertime;
import com.schedule.easy.springboot.domain.opertime.OpertimeRepository;
import com.schedule.easy.springboot.web.dto.OpertimeRequestDto;
import com.schedule.easy.springboot.web.dto.OpertimeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OpertimeService {

    private final OpertimeRepository opertimeRepository;

    @Transactional(readOnly = true)
    public List<OpertimeResponseDto> findListByKey(Long userId, Long groupId) {
        List<Opertime> opertimeList = opertimeRepository.findListByKey(userId, groupId);
        List<OpertimeResponseDto> opertimeResponseDtoList = new ArrayList<OpertimeResponseDto>();
        for(Opertime opertime : opertimeList) { opertimeResponseDtoList.add(new OpertimeResponseDto(opertime)); }
        return opertimeResponseDtoList;
    }

    @Transactional
    public Long save(OpertimeRequestDto requestDto) {
        return opertimeRepository.save(requestDto.toEntity()).getOpertimeId();
    }
}
