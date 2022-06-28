package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student", method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE})
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> getStudents (){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId
    ) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentId,studentName,email);
    }

}
