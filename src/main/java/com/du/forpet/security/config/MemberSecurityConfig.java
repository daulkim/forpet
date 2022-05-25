package com.du.forpet.security.config;

import com.du.forpet.security.MemberUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
public class MemberSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberUserDetailsService memberUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**",
                "/webjars/**",
                "/js/**",
                "/image/**",
                "/fonts/**",
                "/lib/**",
                "/api/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/logout", "/", "/authenticate").permitAll()
            .anyRequest().hasRole("USER");

        http.formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/authenticate")
            .usernameParameter("loginId")
            .passwordParameter("password")
            .defaultSuccessUrl("/")
            .permitAll();

        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login")
            .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberUserDetailsService).passwordEncoder(passwordEncoder);
    }
}

