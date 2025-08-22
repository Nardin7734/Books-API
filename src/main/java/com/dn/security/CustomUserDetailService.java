package com.dn.security;

import com.dn.data.dto.UserDTO;
import com.dn.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class CustomUserDetailService implements UserDetailsService
{

    private Logger logger = Logger.getLogger(CustomUserDetailService.class.getName());

    @Autowired
    private UserServices userServices;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
    {
        UserDTO user;
        try
        {
            user = userServices.findByEmail( username );
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        UserPrincipal up  = new UserPrincipal();
        up.setUserId( user.getId() );
        up.setEmail( user.getEmail() );
        up.setAuthorities(List.of( new SimpleGrantedAuthority( user.getRole() )) );
        up.setUserId( user.getId() );
        up.setPassword( user.getPassword() );


        return up;
    }
}
