package com.du.forpet.domain.entity;


import javax.persistence.*;

import com.du.forpet.domain.PetStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Pet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PET_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column( name="FUR_TYPE" )
    private String furType;

    @Column(name="MEMO")
    private String memo;

    @Column(name="STATUS")
    private PetStatus status;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;


    @OneToMany(mappedBy = "pet")
    private List<Reservation> reservations;

    @Builder
    public Pet(String name, String furType, String memo, PetStatus status, Member member) {
        this.name = name;
        this.furType = furType;
        this.memo = memo;
        this.status = status;
        this.member = member;
    }

    public void update(String name, String memo) {
        this.name = name;
        this.memo = memo;
    }

    public void inactivate() {
        if (status == PetStatus.ACTIVE) {
            this.setStatus(PetStatus.INACTIVE);
        }
    }

    private void setStatus(PetStatus status) {
        this.status = status;
    }
}

