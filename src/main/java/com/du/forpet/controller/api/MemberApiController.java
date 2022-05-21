package com.du.forpet.controller.api;

import com.du.forpet.domain.dto.MemberSaveRequestDto;
import com.du.forpet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    public Long join(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.save(memberSaveRequestDto);
    }
}
