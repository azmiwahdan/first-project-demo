package com.exalt.demo.soap;

import io.spring.guides.gs_producing_web_service.Student;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class StudentRepository {
    private static final Map<String, Student> STUDENTS = new HashMap<>();

    @PostConstruct
    public void initializeData() {
        Student student = new Student(0, "Azmi", "Computer Science");
        Student student1 = new Student(1, "Jihad", "Computer System");
        Student student2 = new Student(2, "Firnas", "Chemistry");
        Student student3 = new Student(3, "Layth", "Computer Science");
        Student student4 = new Student(4, "Muath", "Computer Science");
        STUDENTS.put(student.getName(), student);
        STUDENTS.put(student1.getName(), student1);
        STUDENTS.put(student2.getName(), student2);
        STUDENTS.put(student3.getName(), student3);
        STUDENTS.put(student4.getName(), student4);
    }

    public Student findStudent(String name) {
        Assert.notNull(name, "The Student's name must not be null");
        return STUDENTS.get(name);
    }
}
