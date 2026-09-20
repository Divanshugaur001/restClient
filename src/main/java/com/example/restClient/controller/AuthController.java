package com.example.restClient.controller;

import com.example.restClient.dto.LoginResponseDto;
import com.example.restClient.dto.LoginRrquestDto;
import com.example.restClient.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public LoginResponseDto login(@RequestBody  LoginRrquestDto loginRrquestDto){
        Authentication authreq= UsernamePasswordAuthenticationToken.unauthenticated(loginRrquestDto.getEmail(),loginRrquestDto.getPassword());
        Authentication authentication=authenticationManager.authenticate(authreq);
        String tokken=jwtService.generateToken(authentication);
        return new LoginResponseDto(tokken);

    }
}
