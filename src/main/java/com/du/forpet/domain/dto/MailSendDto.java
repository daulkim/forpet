package com.du.forpet.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MailSendDto {

    private String receiver;
    private String subject;
    private String text;

    @Builder
    public MailSendDto(String receiver,
                     String subject,
                     String text) {
        this.receiver = receiver;
        this.subject = subject;
        this.text = text;
    }
}
