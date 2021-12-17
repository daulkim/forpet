package com.du.forpet.study;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebClientApi {

    @PostMapping("/api/test")
    public String getResultTest(@RequestBody String result) {
        return "API RESULT "+result;
    }
}
