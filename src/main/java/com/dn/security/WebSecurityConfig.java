package com.dn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{
    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain appSecurity(HttpSecurity http ) throws Exception
    {
        http.addFilterBefore( filter, UsernamePasswordAuthenticationFilter.class );

        http.cors( Customizer.withDefaults() )
                .csrf( AbstractHttpConfigurer::disable )
                .sessionManagement(
                        session -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
                .formLogin(AbstractHttpConfigurer::disable)
                .securityMatcher( "/**" )
                .authorizeHttpRequests( reg -> reg.requestMatchers( "/", "/login" )
                        .permitAll().anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config ) throws Exception
    {
        return config.getAuthenticationManager();
    }
}
