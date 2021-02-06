package com.nahara.toka.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User; // !!! USER <---
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import static com.newjerseysoftware.hederaDemo.security.SecurityConstants.EXPIRATION_TIME;
import static com.newjerseysoftware.hederaDemo.security.SecurityConstants.HEADER_STRING;
import static com.newjerseysoftware.hederaDemo.security.SecurityConstants.SECRET;
import static com.newjerseysoftware.hederaDemo.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    static final Logger log =
            LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
{

        try {

            com.newjerseysoftware.hederaDemo.model.User loginUser =
                    new ObjectMapper()
                            .readValue(request.getInputStream(), com.newjerseysoftware.hederaDemo.model.User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.addHeader("Access-Control-Expose-Headers",
                "Content-Disposition,X-Suggested-Filename,X-Auth-Token,Authorization");
    }
}*/
