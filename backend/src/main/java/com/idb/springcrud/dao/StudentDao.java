package com.idb.springcrud.dao;

import com.idb.springcrud.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentDao {
    private final JdbcTemplate jdbcTemplate;
    private long maxId = 0;

    public Student save(Student data) {
        if (data.hasId()) {
            String sql = "update student set " + "name = ?, " + "email = ?, " + "password = ?, " + "designation = ?, " + "department = ?, " + "salary = ? " + "where id = ?";
            int result = jdbcTemplate.update(sql, new Object[]{data.getName(), data.getEmail(), data.getPassword(), data.getDesignation(), data.getDepartment(), data.getSalary(), data.getId()});
            if (result > 0) {
                return data;
            }
        } else {
            String sql = "insert into student (id, name , email, password, designation, department, salary) values(?,?,?,?,?,?,?)";
            int result = jdbcTemplate.update(sql, new Object[]{getMaxId(), data.getName(), data.getEmail(), data.getPassword(), data.getDesignation(), data.getDepartment(), data.getSalary()});
            if (result > 0) {
                return data;
            }
        }
        return new Student();
    }

    public List<Student> findAll() {
        String sql = "select * from student";
        List<Student> students = jdbcTemplate.query(sql, (rs, rownum) -> {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            student.setPassword(rs.getString("password"));
            student.setDesignation(rs.getString("designation"));
            student.setDepartment(rs.getString("department"));
            student.setSalary(rs.getString("salary"));
            return student;
        });
        return students;
    }

    public int deleteById(long id) {
        String sql = "delete from student where id = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    private Long getMaxId() {
        if (maxId == 0) {
            String sql = "select coalesce(max(id), 0) from student";
            maxId = jdbcTemplate.queryForObject(sql, Long.class);
        }
        return ++maxId;
    }

}
