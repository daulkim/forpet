package com.du.forpet.domain.entity;

import javax.persistence.*;

import com.du.forpet.domain.ActivityStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "STATUS")
    private ActivityStatus status;

    @Column(name = "PENALTY")
    private int penalty;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Pet> pets;

    @Builder
    public Member(String email,
                  String password,
                  String name,
                  String phoneNumber,
                  ActivityStatus status,
                  int penalty,
                  Address address){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.penalty = penalty;
        this.address = address;
    }

    public void update(String password, String name, String phoneNumber) {
        this.password = password;
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