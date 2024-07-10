package com.sparta.spartabackoffice.admin.service;

import com.sparta.spartabackoffice.admin.dto.AdminRequestDto;
import com.sparta.spartabackoffice.admin.entity.Admin;
import com.sparta.spartabackoffice.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Admin registerAdmin(AdminRequestDto adminRequestDto) {
        logger.info("Registering new admin with email: {}", adminRequestDto.getEmail());

        // 인코딩 전후 비밀번호 로그 출력
        String rawPassword = adminRequestDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        logger.info("Raw password: {}", rawPassword);
        logger.info("Encoded password: {}", encodedPassword);

        Admin admin = new Admin();
        admin.setEmail(adminRequestDto.getEmail());
        admin.setPassword(encodedPassword);
        admin.setDepartment(adminRequestDto.getDepartment());
        admin.setRole(adminRequestDto.getRole());

        if (admin.getRole() == Admin.Role.MANAGER &&
                admin.getDepartment() != Admin.Department.CURRICULUM &&
                admin.getDepartment() != Admin.Department.DEVELOPMENT) {
            logger.warn("Attempt to assign MANAGER role to non-eligible department: {}", admin.getDepartment());
            throw new IllegalArgumentException("커리큘럼, 개발 부서만 MANAGER 권한을 부여 받을 수 있습니다.");
        }

        Admin savedAdmin = adminRepository.save(admin);
        logger.info("Admin registered successfully: {}", savedAdmin.getEmail());
        return savedAdmin;
    }

    public boolean authenticate(String email, String password) throws IllegalArgumentException {
        logger.info("Attempting authentication for email: {}", email);

        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isEmpty()) {
            logger.warn("Authentication failed: No admin found with email: {}", email);
            return false;
        }

        Admin admin = adminOptional.get();
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            logger.warn("Authentication failed: Incorrect password for email: {}", email);
            return false;
        }

        logger.info("Authentication successful for email: {}", email);
        return true;
    }
}
