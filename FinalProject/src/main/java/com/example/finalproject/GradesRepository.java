package com.example.finalproject;

import org.springframework.data.repository.CrudRepository;

public interface GradesRepository extends CrudRepository<Grades, Integer> {
    Grades findGradesByGid(Integer gid);
}
