package com.sparta.spartabackoffice.instructor.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class InstructorRequestDto {
    @NotEmpty
    private String name;

    private int experience;

    @NotEmpty
    private String company;

    @NotEmpty
    private String phone;

    private String description;


}
