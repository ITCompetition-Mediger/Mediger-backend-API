package com.cos.mediAPI.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cos.mediAPI.MedigerBackendApiApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity  // 해당 애노테이션을 붙인 필터(현재 클래스)를 스프링 필터체인에 등록.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 커스텀한 OAuth2UserService DI.
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

	// encoder를 빈으로 등록.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
    	System.out.println("BCrypePasswordEncoder");
        return new BCryptPasswordEncoder();
    }


    // WebSecurity에 필터를 거는 게 훨씬 빠름. HttpSecrity에 필터를 걸면, 이미 스프링 시큐리티 내부에 들어온 상태기 때문에..
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/members/**","/image/**");    // /image/** 있는 모든 파일들은 시큐리티 적용을 무시한다.
       // web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());    // 정적인 리소스들에 대해서 시큐리티 적용 무시.
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http.authorizeRequests()
        			.antMatchers().permitAll() // 커스텀 로그인 페이지를 만든 경우 권한을 수동으로 모두 접근 가능하도록 변경
                    .anyRequest()	// 모든 요청에 대해서 허용하라.
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JESSIONID","remember-me")
                    .logoutSuccessUrl("/logout").permitAll()	// 로그아웃에 대해서 성공하면 "/"로 이동
                .and()
                    .oauth2Login().loginPage("/login").permitAll() //OAuth2 로그인 설정에서 로그인 페이지 URL을 수동으로 변경
                    .defaultSuccessUrl("/home")
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);	// oauth2 로그인에 성공하면, 유저 데이터를 가지고 우리가 생성한
             	       						// customOAuth2UserService에서 처리를 하겠다. 그리고 "/login-success"로 이동하라.
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
    	System.out.println("CorsConfigure");
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}