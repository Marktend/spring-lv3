package com.sparta.spartabackoffice.admin.controller;

import com.sparta.spartabackoffice.admin.dto.AdminRequestDto;
import com.sparta.spartabackoffice.admin.dto.LoginRequestDto;
import com.sparta.spartabackoffice.admin.dto.LoginResponseDto;
import com.sparta.spartabackoffice.admin.entity.Admin;
import com.sparta.spartabackoffice.admin.jwtutil.JwtUtil;
import com.sparta.spartabackoffice.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody AdminRequestDto adminRequestDto) {
        Admin admin = adminService.registerAdmin(adminRequestDto);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            if (adminService.authenticate(loginRequestDto.getEmail(), loginRequestDto.getPassword())) {
                String token = jwtUtil.generateToken(loginRequestDto.getEmail());

                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "Bearer " + token);

                LoginResponseDto responseDto = new LoginResponseDto(token, "로그인 성공");
                return ResponseEntity.ok().headers(headers).body(responseDto);
            } else {
                return ResponseEntity.badRequest().body(new LoginResponseDto(null, "로그인 실패: 이메일 또는 비밀번호가 올바르지 않습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponseDto(null, "서버 오류가 발생했습니다."));
        }
    }
}
