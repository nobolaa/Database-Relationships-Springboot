package com.example.relationshipsSpringboot.subject;

import com.example.relationshipsSpringboot.student.Student;
import com.example.relationshipsSpringboot.subject.Subject;
import com.example.relationshipsSpringboot.teacher.Teacher;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToMany
    @JoinTable(
            name = "subject_student",
            joinColumns = { @JoinColumn(name = "subject_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private Set<Student> students = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }
    public void enrollStudents(Student student) {
        students.add(student);
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void addTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
