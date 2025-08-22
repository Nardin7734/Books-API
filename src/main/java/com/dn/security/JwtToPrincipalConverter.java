package com.interact.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter
{
    public UserPrincipal convert(DecodedJWT jwt )
    {
        UserPrincipal principal = new UserPrincipal();
        principal.setUserId( Long.valueOf( jwt.getSubject() ) );
        principal.setEmail( jwt.getClaim( "e" ).asString() );
        principal.setAuthorities( extractAuthoritiesFromClaims( jwt ) );

        return principal;
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaims( DecodedJWT jwt )
    {
        var claim = jwt.getClaim( "a" );
        if( claim.isNull() || claim.isMissing() ) return List.of();
        return claim.asList( SimpleGrantedAuthority.class );
    }
}
