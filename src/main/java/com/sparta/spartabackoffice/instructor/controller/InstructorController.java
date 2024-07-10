package com.sparta.spartabackoffice.instructor.controller;

import com.sparta.spartabackoffice.instructor.dto.InstructorRequestDto;
import com.sparta.spartabackoffice.instructor.entity.Instructor;
import com.sparta.spartabackoffice.instructor.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping("/register")
    public ResponseEntity<Instructor> registerInstructor(@Valid @RequestBody InstructorRequestDto instructorRequestDto) {
        Instructor instructor = instructorService.registerInstructor(instructorRequestDto);
        return ResponseEntity.ok(instructor);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @Valid @RequestBody InstructorRequestDto instructorRequestDto) {
        Instructor instructor = instructorService.updateInstructor(id, instructorRequestDto);
        return ResponseEntity.ok(instructor);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable Long id) {
        Instructor instructor = instructorService.getInstructor(id);
        return ResponseEntity.ok(instructor);
    }
}
