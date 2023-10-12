package com.idb.springcrud.dao;

import com.idb.springcrud.model.Student;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    List<Student> findAllByEmailLikeOrNameLikeOrAddressLikeOrderByNameDesc(String email, String name, String address);

    @Query("select s from Student s where s.email like ? or s.name like ? or s.address like ?")
    List<Student> findAllByJpql(String email, String name, String address);

    @Query("from Student s where s.email like ? or s.name like ? or s.address like ?")
    List<Student> findAllByHql(String email, String name, String address);

    @Query(value = "select * from student s where s.email like ? or s.name like ? or s.address like ?", nativeQuery = true)
    List<Tuple> findAllByNativeQuery(String email, String name, String address);
//    Object[][] findAllByNativeQuery(String email, String name, String address);
}
