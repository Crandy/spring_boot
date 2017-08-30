package com.zy.study.springboot.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zy on 17-8-28.
 */
@Entity
@Table(name = "roles")
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 50, nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public Roles() {
    }
}
