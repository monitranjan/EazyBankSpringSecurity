package com.monit.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String pwd;
    @Column(name = "role")
    private String role;
}
