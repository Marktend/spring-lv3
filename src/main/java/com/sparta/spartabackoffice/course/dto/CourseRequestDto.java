package com.sparta.spartabackoffice.course.dto;

import com.sparta.spartabackoffice.course.entity.Course;
import com.sparta.spartabackoffice.instructor.entity.Instructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
public class CourseRequestDto {
    @NotEmpty
    private String name;

    private double price;

    @NotEmpty
    private String description;

    @NotNull
    private Course.Category category;

    @NotNull
    private Instructor instructor;

    private Date registerDate;
}
