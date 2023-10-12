package com.idb.springcrud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "paknami_course",
        indexes = {
                @Index(columnList = "\"name\""),
                @Index(columnList = "code"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"\"name\""}),
                @UniqueConstraint(columnNames = {"code"})
        }
)
@Data
public class Course extends BaseEntity {
    @Column(name = "\"name\"", unique = true, columnDefinition = "BLOB")
    private String name;
    @Column(unique = true)
    private String code;
}
