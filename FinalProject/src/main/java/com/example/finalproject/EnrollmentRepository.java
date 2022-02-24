package com.example.finalproject;

import org.springframework.data.repository.CrudRepository;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {
    Enrollment findEnrollmentByEid(Integer eid);
}
