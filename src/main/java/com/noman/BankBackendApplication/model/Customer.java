package com.noman.BankBackendApplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
//@Table(name = "customer")---if the class name and the table name is different. For example,
// if the class name is "CustomerEntity" but the table name in the database is "customer", then we should use @Table(name = "customer").
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "role")
    private String role;

}
