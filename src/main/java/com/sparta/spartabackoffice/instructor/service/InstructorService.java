package com.sparta.spartabackoffice.instructor.service;

import com.sparta.spartabackoffice.instructor.dto.InstructorRequestDto;
import com.sparta.spartabackoffice.instructor.entity.Instructor;
import com.sparta.spartabackoffice.instructor.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public Instructor registerInstructor(InstructorRequestDto instructorRequestDto) {
        Instructor instructor = new Instructor();
        instructor.setName(instructorRequestDto.getName());
        instructor.setExperience(instructorRequestDto.getExperience());
        instructor.setCompany(instructorRequestDto.getCompany());
        instructor.setPhone(instructorRequestDto.getPhone());
        instructor.setDescription(instructorRequestDto.getDescription());

        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Long id, InstructorRequestDto instructorRequestDto) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("강사를 찾을 수 없습니다."));

        instructor.setExperience(instructorRequestDto.getExperience());
        instructor.setCompany(instructorRequestDto.getCompany());
        instructor.setPhone(instructorRequestDto.getPhone());
        instructor.setDescription(instructorRequestDto.getDescription());

        return instructorRepository.save(instructor);
    }

    public Instructor getInstructor(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("강사를 찾을 수 없습니다."));
    }
}
