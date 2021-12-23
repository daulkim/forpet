package com.du.forpet.controller;

import com.du.forpet.domain.KakaoReadyResponseDto;
import com.du.forpet.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KaKaoPayContoller {

    private final KakaoPayService kakaoPayService;

    @GetMapping("/pay/test")
    public KakaoReadyResponseDto testReady() {

        return kakaoPayService.ready();
    }
}
