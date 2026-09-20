package com.example.restClient.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class SignupRequestDto {
    private  String email;
    private String name;
    private String password;
}
