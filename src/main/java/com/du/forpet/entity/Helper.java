package com.du.forpet.entity;

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

    @Column(name = "HELPER_TYPE")
    private String helperType;

}
