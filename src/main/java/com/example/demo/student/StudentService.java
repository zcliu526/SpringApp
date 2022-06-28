package com.example.demo.student;

import net.bytebuddy.pool.TypePool;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {

        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentBy(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exists"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        //Optional<Student> foundStudent = studentRepository.findById(student.getId());
        Student foundStudent = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new IllegalStateException(
                                "student with id " + studentId + " does not exist"
                        )
                );
        if( name != null && name.length() > 0 && !Objects.equals(foundStudent.getName(), name)){
            foundStudent.setName(name);
        }

        if( email != null && email.length() > 0 && !Objects.equals(foundStudent.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentBy(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            foundStudent.setEmail(email);
        }

        studentRepository.save(foundStudent);
    }
}
