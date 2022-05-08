package com.example.relationshipsSpringboot.subject;

import com.example.relationshipsSpringboot.student.Student;
import com.example.relationshipsSpringboot.subject.Subject;

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
}
