package com.example.finalproject;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {
    Enrollment findEnrollmentByEid(Integer eid);
    List<Enrollment> getEnrollmentByCourseIdOrderByEid(Integer courseId);
    List<Enrollment> getEnrollmentByStudentIdOrderByEid(Integer studentId);
}
