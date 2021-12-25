package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class FavouriteAreas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id ;
    private String source;
    private double price;
}
