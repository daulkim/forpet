package com.du.forpet.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
public class ServiceTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVICE_TIME_ID")
    private Long id;

    @Column(name = "SERVICE_TIME")
    private LocalTime serviceTime;

}
