package com.du.forpet.domain.entity;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.dto.HelperScheduleSaveRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Helper {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HELPER_ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "HELPER_TYPE")
    private String helperType;

    @Column(name="STATUS")
    private ActivityStatus status;

    @OneToMany(mappedBy = "helper")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "helper")
    private List<HelperSchedule> helperSchedules = new ArrayList<>();

    @Builder
    public Helper(String email,
                  String password,
                  String name,
                  String phoneNumber,
                  String helperType,
                  ActivityStatus status,
                  List<HelperSchedule> helperSchedules) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.helperType = helperType;
        this.status = status;
        this.helperSchedules = helperSchedules;
    }

    public void update(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void resign(Long id) {
        if (status == ActivityStatus.ACTIVE) {
            this.status = ActivityStatus.INACTIVE;
        }
        else {
            throw new IllegalStateException("해당 회원은 탈퇴할 수 없는 상태입니다."+id);
        }
    }
}
