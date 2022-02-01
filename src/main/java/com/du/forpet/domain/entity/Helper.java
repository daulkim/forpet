package com.du.forpet.domain.entity;

import com.du.forpet.domain.ActivityStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Helper extends BaseTimeEntity {

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

    @Column(name="DISTRICT")
    private String district;

    @OneToMany(mappedBy = "helper")
    private List<Reservation> reservations;

    @Builder
    public Helper(String email,
                  String password,
                  String name,
                  String phoneNumber,
                  String helperType,
                  ActivityStatus status,
                  String district) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.helperType = helperType;
        this.status = status;
        this.district = district;
    }

    public void update(String name, String phoneNumber, String district) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.district = district;
    }

    public void withdraw(Long id) {
        if (status == ActivityStatus.ACTIVE || status == ActivityStatus.UNAUTHORIZED ) {
            this.status = ActivityStatus.INACTIVE;
        }
        else {
            throw new IllegalStateException("해당 회원은 이미 탈퇴한 회원입니다. id: "+id);
        }
    }

    public void approve(Long id) {

        if (status == ActivityStatus.UNAUTHORIZED) {
            this.status = ActivityStatus.ACTIVE;
        }
        else {
            throw new IllegalStateException("해당 회원은 승인할 수 없는 상태입니다."+id);
        }

    }
}
