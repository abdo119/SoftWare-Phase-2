package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id;
    @Column
    protected String destination;
    @Column
    protected String source;
    @Column
    protected double price;
    @Column
    protected double discount = 0;
    @Column
    protected int numberOfPassenger;
    @OneToOne
    @JoinColumn(name = "driver_id")
    protected Driver driver;
    @Column
    boolean flag = true;
}
