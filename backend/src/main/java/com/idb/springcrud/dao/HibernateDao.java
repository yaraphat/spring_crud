package com.idb.springcrud.dao;

import com.idb.springcrud.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateDao {
    private final EntityManager entityManager;

    @Transactional
    public Student save(Student data) {
        if (data.hasId()) {
            List list = findUsingJpql(data);
            entityManager.merge(data);
        } else {
           entityManager.persist(data);
        }
        return new Student();
    }

    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("from Student e", Student.class);
        return query.getResultList();
    }

    public List<Student> findUsingJpql(Student params) {
        String sql = "select s from Student s ";
        String conditions = (params.hasId() ? " s.id = " + params.getId() : "")
                + (StringUtils.hasText(params.getName()) ? ((params.hasId() ? " and " : "") + (" s.name = '" + params.getName() + "'")) : "")
                + (StringUtils.hasText(params.getEmail()) ? (StringUtils.hasText(params.getName()) ? " and" : "") + " s.email = '" + params.getEmail()  + "'": "")
                + (StringUtils.hasText(params.getPassword()) ? (StringUtils.hasText(params.getEmail()) ? " and" : "") + " s.password = '" + params.getPassword()  + "'": "")
                + (StringUtils.hasText(params.getDesignation()) ? (StringUtils.hasText(params.getPassword()) ? " and" : "") + " s.designation = '" + params.getDesignation()  + "'": "")
                + (StringUtils.hasText(params.getDepartment()) ? (StringUtils.hasText(params.getDesignation()) ? " and" : "") + " s.department = '" + params.getDepartment()  + "'": "")
                + (StringUtils.hasText(params.getSalary()) ? (StringUtils.hasText(params.getDepartment()) ? " and" : "") + " s.salary = '" + params.getSalary()  + "'": "")
                ;
        if(StringUtils.hasText(conditions)) {
            sql += " where " + conditions;
        }
        Query query = entityManager.createQuery(sql);
        List result = query.getResultList();
        return result;
    }

    public Student findById(long id) {
        Student student = new Student();
        student.setId(id);
        return entityManager.find(Student.class, student);
    }

    @Transactional
    public void deleteById(long id) {
        Student student = new Student();
        student.setId(id);
        entityManager.remove(student);
    }

}
