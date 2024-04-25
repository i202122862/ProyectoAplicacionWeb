package com.example.demo.security;

import com.example.demo.modelo.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private JWTUtils jwtUtils;

    public JWTAuthenticationFilter(JWTUtils jwtUtils){
        this.jwtUtils=jwtUtils;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        UserEntity userToaEntity=null;
        String username="";
        String password="";

        try {
            userToaEntity=new ObjectMapper().readValue(request.getInputStream(),UserEntity.class);
            username=userToaEntity.getUsername();
            password=userToaEntity.getPassword();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);


        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        User user=(User) authResult.getPrincipal();
        String token=jwtUtils.generateAccessToken(user.getUsername());

        response.addHeader(Constants.HEADER_AUTHORIZATION_KEY,token);
        Map<String,Object> httpResponse=new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message","Autenticación correcta");
        httpResponse.put("Username",user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}