package com.example.relationshipsSpringboot.subject;

import com.example.relationshipsSpringboot.student.Student;
import com.example.relationshipsSpringboot.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    Subject createSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/students/{studentId}")
    Subject enrollStudentToSubject(@PathVariable Long subjectId, @PathVariable Long studentId){
        Subject subject = subjectRepository.getById(subjectId);
        Student student = studentRepository.getById(studentId);

        subject.enrollStudents(student);
        return subjectRepository.save(subject);
    }
}
