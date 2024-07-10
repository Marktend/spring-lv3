package com.sparta.spartabackoffice.course.controller;

import com.sparta.spartabackoffice.course.dto.CourseRequestDto;
import com.sparta.spartabackoffice.course.entity.Course;
import com.sparta.spartabackoffice.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/register")
    public ResponseEntity<Course> registerCourse(@Valid @RequestBody CourseRequestDto courseRequestDto) {
        Course course = courseService.registerCourse(courseRequestDto);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequestDto courseRequestDto) {
        Course course = courseService.updateCourse(id, courseRequestDto);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Course course = courseService.getCourse(id);
        return ResponseEntity.ok(course);
    }
}
