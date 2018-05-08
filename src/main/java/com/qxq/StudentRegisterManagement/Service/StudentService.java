package com.qxq.StudentRegisterManagement.Service;

import com.qxq.StudentRegisterManagement.Entity.Student;
import com.qxq.StudentRegisterManagement.StudentDao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Component
public class StudentService {

    @Autowired
    @Qualifier("MySQL")
    StudentDao studentDao;

    public Collection<Student> getAllStudents(){
        return this.studentDao.getAllStudents();
    }

    public void registerStudent(Student student) {
        this.studentDao.registerStudent(student);
    }

    public Student getStudentById(int id){
        return this.studentDao.getStudentById(id);
    }

    public void deleteStudentById(int id){
        this.studentDao.deleteStudentById(id);
    }

    public void updateStudent(Student student){
        this.studentDao.updateStudent(student);
    }

}
