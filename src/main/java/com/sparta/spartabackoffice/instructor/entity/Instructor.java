package com.sparta.spartabackoffice.instructor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotBlank(message = "강사 이름은 필수입니다")
    private String name;

    @NotNull(message = "경력 연수는 null이 아니어야 합니다")
    @Column(name = "experience", columnDefinition = "INT DEFAULT 0")
    private int experience; // 기본값이 있는 원시 int 타입 필드

    private String company;

    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}", message = "올바른 전화번호 형식이 아닙니다 (###-####-#### 형식)")
    private String phone;

    @Size(max = 500, message = "설명은 500자 이하여야 합니다")
    private String description;

    // Constructors, getters, setters, etc.
}
