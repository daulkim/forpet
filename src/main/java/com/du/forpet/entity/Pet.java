package com.du.forpet.entity;


import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Pet {

    @Id @GeneratedValue
    @Column(name="PET_ID")
    private Long id;

    private String name;

    @Column( name="FUR_TYPE" )
    private String furType;

    private String memo;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "pet")
    private List<BathReservation> bathReservations;

    @Builder
    public Pet(String name, String memo){
        this.name = name;
        this.memo = memo;
    }
}

