package com.du.forpet.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Helper {

    @Id @GeneratedValue
    @Column(name = "HELPER_ID")
    private Long id;

    private String email;

    private String password;

    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "HELPER_TYPE")
    private String helperType;

    @Builder
    public Helper(String email,
                  String password,
                  String name,
                  String phoneNumber,
                  String helperType) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.helperType = helperType;
    }

}
