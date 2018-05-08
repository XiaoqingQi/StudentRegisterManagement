package com.qxq.StudentRegisterManagement.Controller;

import com.qxq.StudentRegisterManagement.Entity.Student;
import com.qxq.StudentRegisterManagement.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/students")

public class StudentController {

    @Autowired
    public StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents() {
        return this.studentService.getAllStudents();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerStudent(@RequestBody Student student) {
        this.studentService.registerStudent(student);
    }

    @RequestMapping(value = ("/{id}"), method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id){
        return this.studentService.getStudentById(id);
    }

    @RequestMapping(value = ("/{id}"), method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id){
        this.studentService.deleteStudentById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody Student student){
        this.studentService.updateStudent(student);
    }
}
