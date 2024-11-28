package com.akhilp.RBAC.controller;

import com.akhilp.RBAC.model.Student;
import com.akhilp.RBAC.model.StudentMarksDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(
        List.of(new Student(1,"kiran",80),new Student(2,"akhil",70))
    );

    @GetMapping("/students/names")
    public List<String> getStudentNames() {
        List<String> names = new ArrayList<>();
        for (Student student : students) {
            names.add(student.getName());
        }
        return names;
    }

    @Secured({"ROLE_MODERATOR", "ROLE_ADMIN"})
    @GetMapping("/students/marks")
    public List<StudentMarksDTO> getStudentMarks() {
        List<StudentMarksDTO> result = new ArrayList<>();
        for (Student student : students) {
            result.add(new StudentMarksDTO(student.getName(), student.getMarks()));
        }
        return result;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/students/data")
    public List<Student> getStudentData() {
        return students; // Admin can see all details
    }


    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student)
    {
        students.add(student);
        return student;
    }
}
