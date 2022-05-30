package com.du.forpet.security;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.entity.Authority;
import com.du.forpet.domain.entity.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HelperUserDetails implements UserDetails, Serializable {

    private String id;
    private String loginId;
    private String password;
    private String nickname;
    private ActivityStatus status;
    private Set<ServiceType> serviceTypes;
    private Set<Authority> auth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = auth.stream()
                                                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                                                        .collect(Collectors.toList());
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public HelperUserDetails(String loginId, String password, Set<ServiceType> serviceTypes, Set<Authority> auth) {
        this.loginId = loginId;
        this.password = password;
        this.serviceTypes = serviceTypes;
        this.auth = auth;
    }
}
