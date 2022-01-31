package com.du.forpet.service;

import com.du.forpet.domain.KakaoReadyRequestDto;
import com.du.forpet.domain.KakaoReadyResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KakaoPayService {

    private String key;

    private WebClient webClient = WebClient
                                    .builder()
                                    .baseUrl("https://kapi.kakao.com")
                                    .build();
    public KakaoReadyResponseDto ready() {
        System.out.println("check key: " +key);
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
                .header("Authorization","KakaoAK "+key)
                .header("Content-type","application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(KakaoReadyResponseDto.class).block();

    }
}
