package com.example.restClient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginRrquestDto {
    private String email;
    private String password;
}
