package com.angularspring.crud.service;

import com.angularspring.crud.model.Message;
import com.angularspring.crud.model.Student;
import com.angularspring.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Message addStudent(Student student) {
        Message message = isUserNameExist(student);
        if (message.isFlag()) {
            studentRepository.save(student);
            message.setMessage("Student added successfully");
        }
        return message;
    }

    public Message deleteStudent(long id) {
        Message message = new Message();
        if (studentRepository.findById(id) != null) {
            studentRepository.deleteById(id);
            message.setMessage("Student Deleted Successfully");
            message.setFlag(true);
        } else {
            message.setMessage("Student does not exist");
            message.setFlag(false);
        }
        return message;
    }

    public Student updateStudent(Student student) {
        if (isUserExist(student)) {
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    public Student isAuthenticated(String userName, String password) {
        Student student = null;
        Student existingStudent = studentRepository.findByUserName(userName);
        if (existingStudent != null) {
            if (existingStudent.getPassword().equals(password)) {
                student = existingStudent;
            } else {
                student = null;
            }
        }
        return student;

    }

    public Message isUserNameExist(Student student) {
        Student existingStudent = studentRepository.findByUserName(student.getUserName());
        Message message = new Message();
        if (existingStudent != null) {
            message.setMessage("User Name Already Exists");
            message.setFlag(false);
        } else {
            message.setMessage("User Name does Not Exist");
            message.setFlag(true);
        }
        return message;
    }

    public boolean isUserExist(Student student) {

        return studentRepository.existsById(student.getId());

    }
}
