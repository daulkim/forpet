package com.du.forpet.security.config;

import com.du.forpet.security.HelperUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelperSecurityConfig extends WebSecurityConfigurerAdapter {

    private final HelperUserDetailService helperUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()
                .and()
                .requestMatchers()
                .antMatchers("/helpers/**")
                .and()
                .authorizeRequests()
                .antMatchers("/helpers/login", "/helpers/logout", "/", "/helpers/authenticate").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
            .loginPage("/helpers/login")
            .loginProcessingUrl("/helpers/authenticate")
            .usernameParameter("loginId")
            .passwordParameter("password")
            .defaultSuccessUrl("/")
            .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/helpers/logout"))
                .logoutSuccessUrl("/helpers/login")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(helperUserDetailService).passwordEncoder(passwordEncoder());
    }
}

