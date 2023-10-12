package com.idb.springcrud.service;

import com.idb.springcrud.dao.HibernateDao;
import com.idb.springcrud.dao.StudentDao;
import com.idb.springcrud.dao.StudentRepository;
import com.idb.springcrud.model.Student;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
//    private final StudentDao studentRepository;
//    private final HibernateDao studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findAsTuple(String email, String name, String address) {
        List<Tuple> data = studentRepository.findAllByNativeQuery(email, name, address);
        return data.stream().map(Student::new).collect(Collectors.toList());
    }
}
