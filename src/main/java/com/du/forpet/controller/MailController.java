package com.du.forpet.controller;

import com.du.forpet.domain.dto.MailSendDto;
import com.du.forpet.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MailController {

    private final MailService mailService;

    @GetMapping("/mail")
    public void send(@RequestBody MailSendDto dto) {
        mailService.sendMail(dto);
    }
}
