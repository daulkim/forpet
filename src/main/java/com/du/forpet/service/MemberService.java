package com.du.forpet.service;

import com.du.forpet.domain.dto.MemberResponseDto;
import com.du.forpet.domain.dto.MemberSaveRequestDto;
import com.du.forpet.domain.dto.MemberUpdateRequestDto;
import com.du.forpet.domain.entity.Member;
import com.du.forpet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(MemberSaveRequestDto requestDto) throws Exception {
        boolean present = memberRepository.findByEmail(requestDto.getEmail()).isPresent();

        if(present) {
            throw new Exception("이미 존재하는 email 입니다.");
        }

        return memberRepository.save(requestDto.toEntity()).getId();
    }

    public MemberResponseDto findById(Long id) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 회원을 찾을 수 없습니다. id: " + id));
        return new MemberResponseDto(entity);
    }

    public Long update(Long id, MemberUpdateRequestDto requestDto) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 회원을 찾을 수 없습니다. id: " + id));

        member.update(requestDto.getPassword(),
                        requestDto.getName(),
                        requestDto.getPhoneNumber());
        return id;
    }

    public void withdraw(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 회원이 존재하지 않습니다. id: " + id));

        member.resign(id);
    }
}
