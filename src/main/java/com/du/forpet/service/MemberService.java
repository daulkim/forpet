package com.du.forpet.service;

import com.du.forpet.domain.dto.MemberSaveRequestDto;
import com.du.forpet.domain.dto.PetResponseDto;
import com.du.forpet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long save(MemberSaveRequestDto memberSaveRequestDto) {

        boolean isExist = memberRepository.existsByLoginId(memberSaveRequestDto.getLoginId());

        try {
            if(isExist)
                throw new Exception("이미 존재하는 아이디 입니다. id: " + memberSaveRequestDto.getLoginId());
        }catch (Exception e) {
            logger.error(e.getMessage());
            return 0L;
        }

        String encodedPassword = passwordEncoder.encode(memberSaveRequestDto.getPassword());
        return memberRepository.save(memberSaveRequestDto.toEntity(encodedPassword)).getId();
    }

    public List<PetResponseDto> findPetByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId)
                                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디 입니다. id: " + loginId))
                                .getPets().stream().map(pet -> new PetResponseDto(pet)).collect(Collectors.toList());
    }
}
