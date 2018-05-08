package com.qxq.StudentRegisterManagement.StudentDao;

import com.qxq.StudentRegisterManagement.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Component
@Qualifier("MySQL")
public class MySQLStudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setPhoneNum(resultSet.getInt("phoneNum"));
            return student;
        }
    }

    public Collection<Student> getAllStudents() {
        final String sql = "SELECT id, name, phoneNum FROM studentRegisterForm";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
        return students;
    }

    public Student getStudentById(int id) {
        final String sql = "SELECT id, name, phoneNum FROM studentRegisterForm WHERE id = " + id;
        List<Student> student = jdbcTemplate.query(sql, new StudentRowMapper());
        if (student.size() == 0){
            return null;
        }
        return student.get(0);
    }

    public void deleteStudentById(int id) {
        final String sql = "DELETE FROM studentRegisterForm WHERE id = " + id;
        jdbcTemplate.update(sql);
    }

    public void registerStudent(Student student) {
        final String sql = "INSERT INTO studentRegisterForm（name, phoneNum) VALUES (" + "'" + student.getName() + "'" +
                ", " + student.getPhoneNum() + ")";
        System.out.println(sql);
        jdbcTemplate.update(sql);

    }

    public void updateStudent(Student student) {
        final String sql = "UPDATE studentRegisterForm SET phoneNum = " + student.getPhoneNum() + "," + "name = " + "'" + student.getName() + "'" +
                " WHERE id = " + student.getId();
        System.out.println(sql);
        jdbcTemplate.update(sql);

    }
}
