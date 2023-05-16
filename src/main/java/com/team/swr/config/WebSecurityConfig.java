package com.team.swr.config;

import com.team.swr.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    JwtAuthenticationFilter jwtAuthenticationFilter;

    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http security builder
        http.cors() // WebMvcConfig에서 이미 설정 했으므로 기본 cors설정
                .and()
                .csrf() // csrf는 현재 사용하지 않으므로 disable
                    .disable()
                .httpBasic()    // token을 사용하므로 basic인증 disable
                    .disable()
                .sessionManagement()    // seeion기반이 아님을 선언
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()    // /와 /auth/**경로는 인증 안함
                    .antMatchers("/", "/auth/**", "/h2-console/**").permitAll()
                .anyRequest()   // /와 /auth/** 이외의 모든 경로는 인증
                    .authenticated();

        // filter 등록 매요청마다 CorsFilter 실행한 후 JwtAuthentication 실행
        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );

        http.headers()
                .frameOptions()
                .sameOrigin();

    }


}
