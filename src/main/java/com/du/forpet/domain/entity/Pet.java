package com.du.forpet.domain.entity;


import javax.persistence.*;

import com.du.forpet.domain.ActivityStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Pet extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PET_ID")
    private Long id;

    @Column(name="PET_NAME")
    private String name;

    @Column( name="FURTYPE" )
    private String furtype;

    @Column(name="MEMO")
    private String memo;

    @Column(name="STATUS")
    private ActivityStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Builder
    public Pet(String name, String furtype, String memo, ActivityStatus status, Member member) {
        this.name = name;
        this.furtype = furtype;
        this.memo = memo;
        this.status = status;
        this.member = member;
    }
}

