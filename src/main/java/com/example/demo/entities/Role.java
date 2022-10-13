package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    private Long id;
    @Column
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role() {

    }
}
