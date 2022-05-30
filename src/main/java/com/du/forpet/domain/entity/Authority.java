package com.du.forpet.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Authority {

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    public static Set<Authority> authoritySet(Set<Authority> authorities) {
        Set<Authority> authoritySet = new HashSet<>();
        authorities.forEach(auth -> authoritySet.add(new Authority(auth)));

        return authoritySet;
    }

    public Authority(Authority authority) {
        this.authorityName = authority.getAuthorityName();
    }
}