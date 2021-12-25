package com.example.demo.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id ;
    @Column
    protected String eventName;
    @Column
    protected LocalDateTime eventTime;
    @Column
    protected double price;
    @Column
    protected String captainName;
    @Column
    protected String customerName;
}
