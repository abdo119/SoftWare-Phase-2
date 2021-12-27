package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id;
    @Column
    protected String username;
    @Column
    protected String email;
    @Column
    protected String phone;
    @Column
    protected String password;

}
