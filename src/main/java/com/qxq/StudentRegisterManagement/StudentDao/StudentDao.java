package com.qxq.StudentRegisterManagement.StudentDao;

import com.qxq.StudentRegisterManagement.Entity.Student;

import java.util.Collection;
import java.util.List;

public interface StudentDao {

    public Collection<Student> getAllStudents();
    public Student getStudentById(int id);
    public void deleteStudentById(int id);
    public void registerStudent(Student student);
    public void updateStudent(Student student);

}
