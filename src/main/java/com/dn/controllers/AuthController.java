package com.dn.controllers;

import com.dn.security.JwtIssuer;
import com.dn.data.dto.LoginDTO;
import com.dn.data.dto.LoginResponseDTO;
import com.dn.security.UserPrincipal;
import com.dn.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/login" )
public class AuthController
{
    @Autowired
    private JwtIssuer jwtIssuer;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServices userServices;

    @PostMapping
    public LoginResponseDTO login(@RequestBody @Validated LoginDTO loginDto ) throws Exception
    {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( loginDto.getEmail(), loginDto.getPassword() )
        );

        SecurityContextHolder.getContext().setAuthentication( authentication );
        var principal = (UserPrincipal) authentication.getPrincipal();
        var roles = principal.getAuthorities().stream().map( GrantedAuthority::getAuthority ).toList();
        var token = jwtIssuer.issue( principal.getUserId(), principal.getEmail(), roles );

        return new LoginResponseDTO( token );
    }

}
