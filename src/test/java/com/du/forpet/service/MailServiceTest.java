package com.du.forpet.service;

import com.du.forpet.domain.dto.MailSendDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void send_test() {
        MailSendDto dto = MailSendDto
                            .builder()
                            .receiver("수신자이메일")
                            .subject("테스트 메일입니다.")
                            .text("본문 테스트")
                            .build();

        mailService.sendMail(dto);
    }
}

