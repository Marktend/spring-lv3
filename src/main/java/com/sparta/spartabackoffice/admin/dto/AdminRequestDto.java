package com.sparta.spartabackoffice.admin.dto;

import com.sparta.spartabackoffice.admin.entity.Admin;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class AdminRequestDto {

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;


    private String password;

    @NotNull(message = "부서를 선택해야 합니다.")
    private Admin.Department department;

    @NotNull(message = "역할을 선택해야 합니다.")
    private Admin.Role role;
}
