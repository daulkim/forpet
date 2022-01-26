package com.du.forpet.controller.api;

import com.du.forpet.domain.dto.HelperUpdateRequestDto;
import com.du.forpet.domain.dto.MemberSaveRequestDto;
import com.du.forpet.domain.dto.MemberUpdateRequestDto;
import com.du.forpet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Long join(@RequestBody MemberSaveRequestDto requestDto) throws Exception {
        return memberService.save(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.update(id, requestDto);
    }

    @PatchMapping("/{id}/withdrawal")
    public void withdraw(@PathVariable Long id) {
        memberService.withdraw(id);
    }

}
