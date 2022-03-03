package com.example.finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/programs")
public class ProgramsController {

    @Autowired
    private ProgramsRepository programsRepository;

    @PostMapping(path = "/addProgram")
    public @ResponseBody String addCourse(@RequestParam String programName, String campus){

        Programs program = new Programs();
        program.setProgramName(programName);
        program.setCampus(campus);
        programsRepository.save(program);
        return "Attempt to add Program: " + program.getProgramName() + " was successful. Congratulations!";
    }

    @GetMapping(path = "/listPrograms")
    public @ResponseBody Iterable<Programs> getPrograms(){
        return programsRepository.findAll();
    }

    @GetMapping(path = "/viewProgram/{pid}")
    public @ResponseBody Programs viewProgram(@PathVariable Integer pid){
        return programsRepository.findProgramsByPid(pid);
    }

    @PutMapping(path = "/modifyProgram/{pid}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Programs> updateProgram(@PathVariable("pid") Integer pid,
                                               @Validated @RequestBody Programs programDetails){
        Programs program = programsRepository.findProgramsByPid(pid);
        program.setProgramName(programDetails.getProgramName());
        program.setCampus(programDetails.getCampus());
        program.setPid(programDetails.getPid());
        final Programs updatedProgram = programsRepository.save(program);
        return ResponseEntity.ok(updatedProgram);
    }

    @DeleteMapping(path = "/deleteProgram/{pid}")
    public String deleteProgram(@PathVariable("pid") Integer pid){
        Programs program = programsRepository.findProgramsByPid(pid);
        programsRepository.delete(program);
        return "Course: " + program.getProgramName() + " was successfully deleted!";
    }

}
