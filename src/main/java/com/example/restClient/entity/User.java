package com.example.restClient.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="roles_id")
    )
    private Set<Roles> roles= new HashSet<>();
}
