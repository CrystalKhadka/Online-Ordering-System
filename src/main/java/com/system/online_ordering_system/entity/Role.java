package com.system.online_ordering_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Role {

    @Id
    private int id;

    @Column(name = "role_name", nullable = false)
    private String name;

}
