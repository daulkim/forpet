package com.du.forpet.domain.dto;

import lombok.Getter;

@Getter
public class MailSendDto {

    private String receiver;
    private String subject;
    private String text;
}
