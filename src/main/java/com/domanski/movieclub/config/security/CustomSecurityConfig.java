package com.domanski.movieclub.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class CustomSecurityConfig {
    private final String USER_ROLE = "USER";
    private final String ADMIN_ROLE = "ADMIN";
    private final String REDACTOR = "REDACTOR";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .mvcMatchers("/ocen-film").authenticated()
                        .mvcMatchers("/komentarz").authenticated()
                        .mvcMatchers("/uzytkownicy").authenticated()
                        .mvcMatchers("/admin/**").hasRole(ADMIN_ROLE)
                        .anyRequest().permitAll())
                .formLogin(login -> login
                        .loginPage("/login").permitAll())
                        .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                                .logoutSuccessUrl("/login?logout").permitAll());
        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"));
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().mvcMatchers(
                "/img/**",
                "/script/**",
                "/styles/**"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
