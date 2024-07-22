package com.jwt.Jwt.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.Jwt.Model.JwtRequest;
import com.jwt.Jwt.config.CustomUserDetailsServiceImpl;
import com.jwt.Jwt.helper.JwtUtil;
import com.jwt.Jwt.response.JwtResponse;

@RestController
public class JwtController {


     @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
    @Autowired
    private JwtUtil jwtUtil;

   

    @Autowired
    private AuthenticationManager authenticationManager;


         @PostMapping("/token")
public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
    System.out.println("Received request " + jwtRequest.getUsername());

    try {
        // Authenticate the user
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
        );
    } catch (UsernameNotFoundException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    } catch (BadCredentialsException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication error");
    }

    // Load user details and generate token
    UserDetails userByUsername = customUserDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
    String token = jwtUtil.generateToken(userByUsername);
    System.out.println("JWT " + token);

List <String> roles =userByUsername.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
System.out.println(roles);


JwtResponse jwtResponse =new JwtResponse(userByUsername.getUsername(), token, roles);
    return ResponseEntity.ok(jwtResponse);
}



}
