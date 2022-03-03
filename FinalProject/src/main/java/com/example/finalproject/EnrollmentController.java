package com.example.finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(path="/enrollment")
public class EnrollmentController {

    @Autowired

    private EnrollmentRepository enrollmentRepository;

    @PostMapping(path = "/addEnrollment")
    public @ResponseBody String addEnrollment(@RequestParam Integer courseId, Integer studentId){

        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(courseId);
        enrollment.setStudentId(studentId);
        enrollmentRepository.save(enrollment);

        return "Enrollment has been added successfully!";
    }

    @GetMapping(path = "/listEnrollmentStudent")
    public @ResponseBody Iterable<Enrollment> getEnrollment(){
        return enrollmentRepository.findAll();
    }


    @PutMapping(path = "/modifyEnrollment/{eid}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable("eid") Integer eid,
                                                  @Validated @RequestBody Enrollment enrollmentDetails){
        Enrollment enrollment = enrollmentRepository.findEnrollmentByEid(eid);
        enrollment.setCourseId(enrollmentDetails.getCourseId());
        enrollment.setStudentId(enrollmentDetails.getStudentId());
        enrollment.setEid(enrollmentDetails.getEid());
        final Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return ResponseEntity.ok(updatedEnrollment);
    }

    @DeleteMapping(path = "/deleteEnrollment/{eid}")
    public String deleteProgram(@PathVariable("eid") Integer eid){
        Enrollment enrollment = enrollmentRepository.findEnrollmentByEid(eid);
        enrollmentRepository.delete(enrollment);
        return "Enrollment has been successfully deleted!";
    }

}
