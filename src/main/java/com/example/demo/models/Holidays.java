package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Setter
@Getter
@Entity
public class Holidays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id;
    @Column
    protected String holidayName;
    @Column
    protected LocalDate date;
}
