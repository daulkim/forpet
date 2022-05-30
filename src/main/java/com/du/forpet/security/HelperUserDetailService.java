package com.du.forpet.security;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.dto.HelperResponseDto;
import com.du.forpet.repository.HelperRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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
        HelperResponseDto helper = new HelperResponseDto(helperRepository.findWithAuthByLoginId(loginId).orElseThrow(() -> new UsernameNotFoundException("존재 하지 않는 아이디 입니다. id: " + loginId)));

        httpSession.setAttribute("helper", new SessionUser(helper));

        return createUser(loginId, helper);
    }

    private HelperUserDetails createUser(String username, HelperResponseDto helper) {
        if (!helper.getStatus().equals(ActivityStatus.ACTIVE)) {
            throw new RuntimeException("사용중인 아이디가 아닙니다. id: " + username);
        }

        return new HelperUserDetails(helper.getLoginId(),
                helper.getPassword(),
                helper.getServiceTypes(),
                helper.getAuthorities());
    }
}
