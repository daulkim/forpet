package com.du.forpet.security;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class HelperUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(MemberUserDetailsService.class);
    private final HelperRepository helperRepository;
    private final HttpSession httpSession;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginId) {
        logger.info("loginId: {}", loginId);
        Optional<Helper> helper = helperRepository.findWithAuthByLoginId(loginId);

        httpSession.setAttribute("helper", new SessionUser(helper.orElse(null)));

        return helper.map(user -> createUser(loginId, user))
                    .orElseThrow(() -> new UsernameNotFoundException("존재 하지 않는 아이디 입니다. id: " + loginId));
    }

    private User createUser(String username, Helper helper) {
        if (!helper.getStatus().equals(ActivityStatus.ACTIVE)) {
            throw new RuntimeException("사용중인 아이디가 아닙니다. id: " + username);
        }

        List<GrantedAuthority> grantedAuthorities = helper.getAuthorities()
                                                            .stream()
                                                            .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                                                            .collect(Collectors.toList());

        return new User(helper.getLoginId(),
                        helper.getPassword(),
                        grantedAuthorities);
    }
}
