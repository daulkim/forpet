package com.du.forpet.controller;

import com.du.forpet.domain.KakaoReadyResponseDto;
import com.du.forpet.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class KaKaoPayController {

    private final KakaoPayService kakaoPayService;

    @GetMapping("/pay/test")
    public void testReady(HttpServletResponse response) throws IOException {
        response.sendRedirect(kakaoPayService.ready().getNext_redirect_pc_url());
    }
}
