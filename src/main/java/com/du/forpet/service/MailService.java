package com.du.forpet.service;

import com.du.forpet.domain.dto.MailSendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(MailSendDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mailDto.getReceiver());
        message.setSubject(mailDto.getSubject());
        message.setText(mailDto.getText());
        mailSender.send(message);
    }
}
