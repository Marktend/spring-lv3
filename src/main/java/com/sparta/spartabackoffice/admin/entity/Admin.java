package com.sparta.spartabackoffice.admin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @Column(unique = true)
    private String email;




    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Department {
        CURRICULUM, MARKETING, DEVELOPMENT
    }

    public enum Role {
        MANAGER, STAFF
    }
}
