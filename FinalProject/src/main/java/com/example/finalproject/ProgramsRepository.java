package com.example.finalproject;

import org.springframework.data.repository.CrudRepository;

public interface ProgramsRepository extends CrudRepository<Programs, Integer> {
    Programs findProgramsByPid(Integer pid);
}
