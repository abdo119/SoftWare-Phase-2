package com.example.demo.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
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
    protected String captainName;
    @Column
    protected String customerName;
    @Column
    protected double price;

    public Events(String eventName, LocalDateTime eventTime, String captainName, String customerName) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.captainName = captainName;
        this.customerName = customerName;
    }
}
