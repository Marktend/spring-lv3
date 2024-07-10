package com.sparta.spartabackoffice.course.repository;

import com.sparta.spartabackoffice.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
