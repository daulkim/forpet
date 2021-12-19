package com.du.forpet.controller;

import com.du.forpet.domain.KakaoReadyRequestDto;
import com.du.forpet.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KaKaoPayContoller {

    private final KakaoPayService kakaoPayService;

    @GetMapping("/pay/test")
    public String testReady() {

        return kakaoPayService.ready();
    }
}
