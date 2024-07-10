package com.sparta.spartabackoffice.course.entity;

import com.sparta.spartabackoffice.instructor.entity.Instructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "강의 이름은 필수입니다")
    private String name;

    private double price;

    private String description;

    @NotNull(message = "카테고리는 필수항목 입니다.")
    @Enumerated(EnumType.STRING)
    private Category category;


    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Temporal(TemporalType.DATE)
    private Date registerDate;

    public enum Category {
        SPRING,
        REACT,
        NODE
    }
}
