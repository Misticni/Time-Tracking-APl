package com.project.demo.security;;

import com.auth0.jwt.JWT;
import com.project.demo.security.exceptions.PasswordsNotTheSameException;
import com.project.demo.users.exceptions.UserNotEnabledException;
import com.project.demo.users.models.Role;
import com.project.demo.users.models.User;
import com.project.demo.users.services.UserService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.project.demo.security.SecurityConstants.*;

@Component
@RequiredArgsConstructor
public class JWTUtils {
    private final UserService userService;

    public List<GrantedAuthority> addAuthoritiesFromRoles(User user, String password) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getEnabled()) {
            if (!userService.passwordMatches(user, password)) {
                throw new PasswordsNotTheSameException();
            }
            for (Role role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
        } else {
            throw new UserNotEnabledException(user.getEmail());
        }

        return authorities;
    }

    private String createAndAppendToken(UserDetails userDetails, HttpServletResponse res) throws IOException {
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withArrayClaim(SecurityConstants.CLAIM_AUTHORITY, (userDetails).getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.getWriter().append(token);

        return token;
    }

    public String createAndAppendToken(HttpServletResponse res, Authentication auth) throws IOException {
        UserDetails userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        return createAndAppendToken(userDetails, res);
    }
}
