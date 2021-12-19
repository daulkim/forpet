package com.du.forpet.service;

import com.du.forpet.domain.KakaoReadyRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KakaoPayService {
    private WebClient webClient = WebClient
                                    .builder()
                                    .baseUrl("https://kapi.kakao.com")
                                    .build();
    public String ready() {
        KakaoReadyRequestDto dto = new KakaoReadyRequestDto();
        dto.setCid("TC0ONETIME");
        dto.setPartner_order_id("orderId");
        dto.setPartner_user_id("userId");
        dto.setItem_name("item");
        dto.setQuantity(1);
        dto.setTotal_amount(1000);
        dto.setTax_free_amount(1);
        dto.setApproval_url("http://localhost:8080");
        dto.setCancel_url("http://localhost:8080");
        dto.setFail_url("http://localhost:8080");
        System.out.println(dto.toString());
        return webClient.post()
                .uri("/v1/payment/ready")
                .bodyValue(dto.toString())
                .header("Authorization","KakaoAK {ADMIN_KEY}")
                .header("Content-type","application/x-www-form-urlencoded;charset=utf-8")
                .exchangeToMono(res -> res.bodyToMono(String.class)).block();

    }
}
