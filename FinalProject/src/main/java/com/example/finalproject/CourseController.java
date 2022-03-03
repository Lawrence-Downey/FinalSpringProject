package com.example.finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping(path = "/addCourse")
    public @ResponseBody String addCourse(@RequestParam String courseName, @RequestParam String courseNumber,
                                          @RequestParam String capacity){

        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseNumber(courseNumber);
        course.setCapacity(capacity);
        courseRepository.save(course);

        return "Attempt to add Course: " + course.getCourseName() + " was successful. Congratulations!";
    }

    @GetMapping(path = "/listCourses")
    public @ResponseBody Iterable<Course> getCourses(){
        return courseRepository.findAll();
    }

    @GetMapping(path = "/viewCourse/{courseId}")
    public @ResponseBody Course viewCourse(@PathVariable Integer courseId){
        return courseRepository.findCourseByCourseId(courseId);
    }

    @PutMapping(path = "/modifyCourse/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable("courseId") Integer courseId,
                                               @Validated @RequestBody Course courseDetails){
        Course course = courseRepository.findCourseByCourseId(courseId);
        course.setCourseName(courseDetails.getCourseName());
        course.setCourseNumber(courseDetails.getCourseNumber());
        course.setCapacity(courseDetails.getCapacity());
        course.setYear(courseDetails.getYear());
        course.setSemester(courseDetails.getSemester());
        course.setPid(courseDetails.getPid());
        final Course updatedCourse = courseRepository.save(course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping(path = "/deleteCourse/{courseId}")
    public String deleteCourse(@PathVariable("courseId") Integer courseId){
        Course course = courseRepository.findCourseByCourseId(courseId);
        courseRepository.delete(course);
        return "Course: " + course.getCourseName() + " was successfully deleted!";
    }

}