package com.team.swr.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    TokenProvider tokenProvider;

    public JwtAuthenticationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {

            // 요청에서 토큰 가져오기
            String token = parseBearerToken(request);
            log.info("Filter is running...");

            // 토큰 검사하기, JWT이므로 인가 서버에 요청하지 않고 검증 가능
            if(token != null && !token.equalsIgnoreCase("null")){
                // userId 가져오기 위조된 경우 예외 처리
                String userId = tokenProvider.validateAndGetUserId(token);
                log.info("Authenticated user ID : " + userId);

                // 인증 완료 SecurityContextholder에 등록해야 인증된 사용자라고 생각
                AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        // 인증된 사용자의 정보 문자열이 아니어도 아무거나 넣을수 있다 보통 UserDetail라는 오브젝트 넣음
                        userId, // <- AuthenticationPrincipal or principal
                        null,
                        AuthorityUtils.NO_AUTHORITIES
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);

            }

        }catch (Exception ex) {
            logger.error("Cloud not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);

    }

    private String parseBearerToken(HttpServletRequest request){
        // http 요청의 헤더를 파싱해 Bearer 토큰 리턴
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }

        return null;
    }



}
