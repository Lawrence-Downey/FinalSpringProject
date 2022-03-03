package com.example.finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(path = "/list/StudentEnrollment/{studentId}")
    public @ResponseBody Iterable<Enrollment> getStudentEnrollment(@PathVariable("studentId") Integer studentId){
        if(studentId != null) {
            return enrollmentRepository.getEnrollmentByStudentIdOrderByEid(studentId);
        }else{
            return null;
        }
    }

    @GetMapping(path = "/list/CourseEnrollment/{courseId}")
    public @ResponseBody Iterable<Enrollment> getCourseEnrollment(@PathVariable("courseId") Integer courseId){
        if(courseId != null) {
            return enrollmentRepository.getEnrollmentByCourseIdOrderByEid(courseId);
        }else{
            return null;
        }
    }


    @PutMapping(path = "/modifyEnrollment/{eid}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
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
