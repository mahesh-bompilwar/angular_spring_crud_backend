package com.angularspring.crud.controller;

import com.angularspring.crud.model.Message;
import com.angularspring.crud.model.Student;
import com.angularspring.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //@CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("getStudents")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping("getStudent/{id}")
    public Student getStudent(@PathVariable(value = "id") long id){
        return studentService.getStudent(id);
    }

    @PostMapping("addStudent")
    public Message addStudent(@RequestBody Student student){
        System.out.println("student.name"+student.getFirstName());
        return studentService.addStudent(student);
    }

    @PutMapping("updateStudent")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @PostMapping(value = "authenticate")
    public Student authenticateStudent(@RequestParam String userName, @RequestParam String password) {
        return studentService.isAuthenticated(userName, password);
    }
    @DeleteMapping ("deleteStudent/{id}")
    public Message deleteStudent(@PathVariable(value = "id") long id){
        return studentService.deleteStudent(id);
    }
}
