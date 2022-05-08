package com.example.relationshipsSpringboot.subject;

import com.example.relationshipsSpringboot.student.Student;
import com.example.relationshipsSpringboot.student.StudentRepository;
import com.example.relationshipsSpringboot.teacher.Teacher;
import com.example.relationshipsSpringboot.teacher.TeacherRepository;
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

    @Autowired
    TeacherRepository teacherRepository;

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
        Subject subject = subjectRepository.getById(subjectId).get();
        Student student = studentRepository.getById(studentId).get();;

        subject.enrollStudents(student);
        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/teachers/{teacherId}")
    Subject enrollTeacherToSubject(@PathVariable Long subjectId, @PathVariable Long teacherId){
        Subject subject = subjectRepository.getById(subjectId).get();;
        Teacher teacher = teacherRepository.getById(teacherId).get();;

        subject.addTeacher(teacher);
        return subjectRepository.save(subject);
    }
}
