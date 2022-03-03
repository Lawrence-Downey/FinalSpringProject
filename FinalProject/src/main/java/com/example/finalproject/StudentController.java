package com.example.finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(path = "/addStudent")
    public String addStudent(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam String email, @RequestParam String address,
                             @RequestParam String city, @RequestParam String postal,
                             @RequestParam String phone) {

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setAddress(address);
        student.setCity(city);
        student.setPostal(postal);
        student.setPhone(phone);
        student.setStudentId(student.getStudentId());
        studentRepository.save(student);

        return "Attempt to add " + student.getFirstName() + " to the database was successful. Congratulations!";
    }

    @GetMapping(path = "/listStudents")
    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/viewStudent/{studentId}")
    public @ResponseBody
    Student viewStudent(@PathVariable Integer studentId) {
        return studentRepository.findStudentByStudentId(studentId);
    }

    @PutMapping(path = "/modifyStudent/{studentId}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") Integer studentId,
                                        @Validated @RequestBody Student studentDetails ){
        Student student = studentRepository.findStudentByStudentId(studentId);
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setAddress(studentDetails.getAddress());
        student.setCity(studentDetails.getCity());
        student.setPostal(studentDetails.getPostal());
        student.setPhone(studentDetails.getPhone());
        final Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping(path = "/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Integer studentId){
        Student student = studentRepository.findStudentByStudentId(studentId);
        studentRepository.delete(student);
        return "Student: " + student.getFirstName() + " has been successfully deleted.";
    }


}
