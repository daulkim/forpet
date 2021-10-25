package com.du.forpet.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class HelperSchedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HELPERSCHEDULE_ID")
    private Long id;

    private String t0900;

    private String t1000;

    private String t1100;

    private String t1200;

    private String t1300;

    private String t1400;

    private String t1500;

    private String t1600;

    private String t1700;

    private String t1800;

    private String t1900;

    private String t2000;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="HELPER_ID")
    private Helper helper;


    @Builder
    public HelperSchedule(String t0900, String t1000, String t1100,
                          String t1200, String t1300, String t1400,
                          String t1500, String t1600, String t1700,
                          String t1800, String t1900, String t2000,
                          Helper helper) {
        this.t0900 = t0900;
        this.t1000 = t1000;
        this.t1100 = t1100;
        this.t1200 = t1200;
        this.t1300 = t1300;
        this.t1400 = t1400;
        this.t1500 = t1500;
        this.t1600 = t1600;
        this.t1700 = t1700;
        this.t1800 = t1800;
        this.t1900 = t1900;
        this.t2000 = t2000;
        this.helper = helper;
    }
}
