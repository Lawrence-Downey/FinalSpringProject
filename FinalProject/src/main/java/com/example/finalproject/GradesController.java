package com.example.finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/grades")
public class GradesController {

    @Autowired
    private GradesRepository gradesRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping(path = "/addGrades")
    public String addGrades(@RequestParam Integer studentId,
                            @RequestParam Integer courseId,
                            @RequestParam String grade){
        Grades grades = new Grades();
        grades.setStudentId(studentId);
        grades.setCourseId(courseId);
        grades.setGrade(grade);
        grades.setGid(grades.getGid());

        Student student = studentRepository.findStudentByStudentId(studentId);
        Course course = courseRepository.findCourseByCourseId(courseId);
        if(student == null || course == null){
            return "You have entered an incorrect Student/Course ID.";
        }else {
            gradesRepository.save(grades);
            return "Attempt to add grade for: " + student.getFirstName() + " " + student.getLastName() + " was successful!";
        }
    }

    @GetMapping(path = "/list/StudentGrades/{studentId}")
    public @ResponseBody Iterable<Grades> getStudentGrades(@PathVariable("studentId") Integer studentId){
        if(studentId != null) {
            return gradesRepository.getGradesByStudentIdOrderByGrade(studentId);
        }else{
            return null;
        }
    }

    @GetMapping(path = "/list/CourseGrades/{courseId}")
    public @ResponseBody Iterable<Grades> getCourseGrades(@PathVariable("courseId") Integer courseId){
        if(courseId != null) {
            return gradesRepository.getGradesByCourseIdOrderByGrade(courseId);
        }else{
            return null;
        }
    }

    /*
    @PutMapping(path = "/modifyGrades/{studentId}")
    public ResponseEntity<Grades> updateGrades(@PathVariable("studentId") Integer studentId,
                                               @Validated @RequestBody Grades gradeDetails){
        Grades grades = gradesRepository.findGradesByStudentIdOrderByGrade(studentId);
        grades.setStudentId(gradeDetails.getStudentId());
        grades.setCourseId(gradeDetails.getCourseId());
        grades.setGrade(gradeDetails.getGrade());
        final Grades updatedGrade = gradesRepository.save(grades);
        return ResponseEntity.ok(updatedGrade);
    }


    @DeleteMapping(path = "/deleteGrades/{studentId}")
    public String deleteGrades(@PathVariable("studentId") Integer studentId){
        Grades grades = gradesRepository.findGradesByStudentIdOrderByGrade(studentId);
        gradesRepository.delete(grades);
        Student student = studentRepository.findStudentByStudentId(studentId);
        studentRepository.delete(student);
        Integer courseId = grades.getCourseId();
        Course course = courseRepository.findCourseByCourseId(courseId);
        courseRepository.delete(course);
        return "Grades have been deleted for: " + student.getFirstName() + " have been successfully deleted!";
    }
    */


}
