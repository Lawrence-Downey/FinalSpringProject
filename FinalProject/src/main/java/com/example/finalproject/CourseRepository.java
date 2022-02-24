package com.example.finalproject;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course findCourseByCourseId(Integer courseId);
}
