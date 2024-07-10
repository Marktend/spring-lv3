package com.sparta.spartabackoffice.instructor.repository;

import com.sparta.spartabackoffice.instructor.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
