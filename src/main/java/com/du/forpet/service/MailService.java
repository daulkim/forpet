package com.du.forpet.service;

import com.du.forpet.domain.dto.MailSendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;

    public void sendMail(MailSendDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo(mailDto.getReceiver());
        message.setSubject(mailDto.getSubject());
        message.setText(mailDto.getText());
        mailSender.send(message);
    }
}
