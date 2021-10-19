package com.du.forpet.controller;

import com.du.forpet.domain.dto.HelperUpdateRequestDto;
import com.du.forpet.domain.dto.MemberSaveRequestDto;
import com.du.forpet.domain.dto.MemberUpdateRequestDto;
import com.du.forpet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Long join(MemberSaveRequestDto requestDto) throws Exception {
        return memberService.save(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, MemberUpdateRequestDto requestDto) {
        return memberService.update(id, requestDto);
    }

    @PatchMapping("/status/{id}")
    public void resign(@PathVariable Long id) {
        memberService.delete(id);
    }

}
