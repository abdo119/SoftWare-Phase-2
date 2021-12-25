package com.example.demo.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity

public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id ;
    @Column
    protected String username;
    @Column
    protected String email;
    @Column
    protected String phone;
    @Column
    protected String password;
    @Column
    protected boolean check ;
    @Column
    protected double balance;
    @Column
    protected LocalDate birthDate;
    @Column
    protected int rideCounter =1;
}
