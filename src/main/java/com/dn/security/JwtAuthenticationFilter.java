package com.interact.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{

    private JwtDecoder decoder;
    private JwtToPrincipalConverter converter;

    public JwtAuthenticationFilter(JwtDecoder decoder, JwtToPrincipalConverter converter)
    {
        this.decoder = decoder;
        this.converter = converter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        extractTokenFromRequest( request )
                .map( decoder::decode )
                .map( converter::convert )
                .map(UserPrincipalAuthenticationToken::new )
                .ifPresent( authentication -> SecurityContextHolder.getContext().setAuthentication( authentication ));
        filterChain.doFilter( request, response );
    }

    private Optional<String> extractTokenFromRequest( HttpServletRequest request )
    {
        var token = request.getHeader( "Authorization" );
        if(StringUtils.hasText( token ) && token.startsWith( "Bearer " ))
        {
            return Optional.of( token.substring( 7 ));
        }
        return Optional.empty();
    }
}
