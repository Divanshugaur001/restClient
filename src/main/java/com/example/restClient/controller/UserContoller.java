package com.example.restClient.controller;

import com.example.restClient.dto.SignupRequestDto;
import com.example.restClient.dto.SignupResponseDto;
import com.example.restClient.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserContoller {
    private final UserService userService;

    public UserContoller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/hello")
    public String sayHello(Authentication authentication){
         return "hello"+authentication.getName();
    }
    @PostMapping("/register")
    public ResponseEntity<SignupResponseDto> register(@RequestBody  SignupRequestDto signupRequestDto){
       SignupResponseDto signupResponseDto=userService.register(signupRequestDto);
       return ResponseEntity.ok(signupResponseDto);
    }
}
