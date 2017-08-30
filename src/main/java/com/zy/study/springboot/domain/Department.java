package com.zy.study.springboot.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zy on 17-8-28.
 */
@Entity
@Table(name = "department")
public class Department implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department() {
    }
}
