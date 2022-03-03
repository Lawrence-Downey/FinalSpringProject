package com.example.finalproject;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradesRepository extends CrudRepository<Grades, Integer> {
    Grades findGradesByGid(Integer gid);
    List<Grades> getGradesByStudentIdOrderByGrade(Integer studentId);
    List<Grades> getGradesByCourseIdOrderByGrade(Integer courseId);

}
