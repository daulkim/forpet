package com.du.forpet.study;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@RestController
public class WebClientTest {

    private final WebClientService webClientService;

    @GetMapping("/test")
    public String getString() {
        String result = webClientService.getResult();
        return result;
    }
}

@Service
class WebClientService {

    private WebClient webClient = WebClient
            .builder()
            .baseUrl("http://localhost:8080")
            .build();

    public String getResult() {
        String result = "SUCCESS";

        return webClient.post()
                .uri("/api/test")
                .bodyValue(result)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
