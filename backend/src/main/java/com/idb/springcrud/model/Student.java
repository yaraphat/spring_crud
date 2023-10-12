package com.idb.springcrud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Tuple;
import lombok.Data;

@Entity
@Data
public class Student extends BaseEntity {
    @Column(name = "\"name\"")
    private String name;
    private String email;
    private String password;
    private String designation;
    private String department;
    private String salary;

    public Student() {
    }

    public Student(Tuple tuple) {
        name = tuple.get("name", String.class);
        email = tuple.get("email", String.class);
        password = tuple.get("password", String.class);
        designation = tuple.get("designation", String.class);
        department = tuple.get("department", String.class);
        salary = tuple.get("salary", String.class);
    }
}
