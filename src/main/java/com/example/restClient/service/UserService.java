package com.example.restClient.service;

import com.example.restClient.dto.SignupRequestDto;
import com.example.restClient.dto.SignupResponseDto;
import com.example.restClient.entity.Roles;
import com.example.restClient.entity.User;
import com.example.restClient.repository.RoleRepository;
import com.example.restClient.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService( UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public SignupResponseDto register(SignupRequestDto signupRequestDto){
        User user = new User();
        user.setEmail(signupRequestDto.getEmail());
        user.setName(signupRequestDto.getName());
        String hashPass=passwordEncoder.encode(signupRequestDto.getPassword());
        user.setPassword(hashPass);
        Roles role= roleRepository.findByName("ROLE_USER").get();
        user.getRoles().add(role);
        userRepository.save(user);

        SignupResponseDto response= new SignupResponseDto();
        response.setName(user.getName());
        response.setMessage("user register successfully");
        return  response;

    }
}
