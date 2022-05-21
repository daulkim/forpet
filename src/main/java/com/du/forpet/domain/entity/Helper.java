package com.du.forpet.domain.entity;

import com.du.forpet.domain.ActivityStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Helper extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HELPER_ID")
    private Long id;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name="STATUS")
    private ActivityStatus status;

//    @Column(name="DISTRICT")
//    private String district;

    @ManyToMany
    @JoinTable(
            name = "HELPER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "HELPER_ID", referencedColumnName = "HELPER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "AUTHORITY_NAME")})
    private Set<Authority> authorities;

    @ManyToMany
    @JoinTable(
            name = "HELPER_SERVICETYPE",
            joinColumns = {@JoinColumn(name = "HELPER_ID", referencedColumnName = "HELPER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SERVICE_NAME", referencedColumnName = "SERVICE_NAME")})
    private Set<ServiceType> serviceTypes;

    @Builder
    public Helper(String loginId,
                  String password,
                  String nickname,
                  String phoneNumber,
                  ActivityStatus status,
                  Set<Authority> authorities,
                  Set<ServiceType> serviceTypes) {

        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.authorities = authorities;
        this.serviceTypes = serviceTypes;
    }

//    public void update(String name, String phoneNumber, String district) {
//        this.name = name;
//        this.phoneNumber = phoneNumber;
//        this.district = district;
//    }
//
//    public void withdraw(Long id) {
//        if (status == ActivityStatus.ACTIVE || status == ActivityStatus.UNAUTHORIZED ) {
//            this.status = ActivityStatus.INACTIVE;
//        }
//        else {
//            throw new IllegalStateException("해당 회원은 이미 탈퇴한 회원입니다. id: "+id);
//        }
//    }
//
//    public void approve(Long id) {
//
//        if (status == ActivityStatus.UNAUTHORIZED) {
//            this.status = ActivityStatus.ACTIVE;
//        }
//        else {
//            throw new IllegalStateException("해당 회원은 승인할 수 없는 상태입니다."+id);
//        }
//
//    }
}
