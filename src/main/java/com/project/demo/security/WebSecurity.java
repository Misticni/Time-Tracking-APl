package com.project.demo.security;
import com.project.demo.users.repositories.UserRepository;
import com.project.demo.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.project.demo.security.SecurityConstants.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class WebSecurity {
    private final UserService userService;
    private final JWTUtils jwtUtils;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity config =  http.cors()
                .and().csrf().disable()
                .authorizeRequests().antMatchers(
                        LOGOUT,
                        PUBLIC_URLS
                )
                .permitAll()
                .anyRequest().authenticated()
                .and();

        config.apply(new JWTAuthenticationFilterDsl(userService, jwtUtils))
                .and().apply(new JWTAuthorizationFilterDsl(userService))
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return config.build();
    }

    @RequiredArgsConstructor
    private static class JWTAuthenticationFilterDsl extends AbstractHttpConfigurer<JWTAuthenticationFilterDsl, HttpSecurity> {
        private final UserService userService;
        private final JWTUtils jwtUtils;

        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

            JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager, userService, jwtUtils);
            http.addFilter(authenticationFilter);
        }
    }

    @RequiredArgsConstructor
    private static class JWTAuthorizationFilterDsl extends AbstractHttpConfigurer<JWTAuthorizationFilterDsl, HttpSecurity> {
        private final UserService userService;

        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

            JWTAuthorizationFilter authenticationFilter = new JWTAuthorizationFilter(authenticationManager, userService);
            http.addFilter(authenticationFilter);
        }
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");

        config.addExposedHeader("WWW-Authenticate");
        config.addExposedHeader("Access-Control-Allow-Origin");
        config.addExposedHeader("Access-Control-Allow-Headers");

        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
