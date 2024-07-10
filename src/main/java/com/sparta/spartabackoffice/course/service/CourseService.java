package com.sparta.spartabackoffice.course.service;

import com.sparta.spartabackoffice.course.dto.CourseRequestDto;
import com.sparta.spartabackoffice.course.entity.Course;
import com.sparta.spartabackoffice.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public Course registerCourse(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setName(courseRequestDto.getName());
        course.setPrice(courseRequestDto.getPrice());
        course.setDescription(courseRequestDto.getDescription());
        course.setCategory(courseRequestDto.getCategory());
        course.setInstructor(courseRequestDto.getInstructor());
        course.setRegisterDate(courseRequestDto.getRegisterDate());

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseRequestDto courseRequestDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));

        course.setName(courseRequestDto.getName());
        course.setPrice(courseRequestDto.getPrice());
        course.setDescription(courseRequestDto.getDescription());
        course.setCategory(courseRequestDto.getCategory());

        return courseRepository.save(course);
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));
    }
}
