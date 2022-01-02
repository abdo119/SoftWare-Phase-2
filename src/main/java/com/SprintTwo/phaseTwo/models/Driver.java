package com.SprintTwo.phaseTwo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Driver {
    private static int count = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id;
    @Column
    protected String username;
    @Column
    protected boolean check;
    @Column
    protected String email;
    @Column
    protected String phone;
    @Column
    protected String password;
    @Column
    protected String driver_license;
    @Column
    protected String national_id;
    @Column
    protected double aveRate = 0;
    @Column
    protected double balance = 0;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn()
    protected List<SuccessfulRide> acceptedRides;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn()
    protected List<FavouriteAreas> favouriteAreas = new ArrayList<>();

    public void setAveRate(double aveRate) {
        this.aveRate = (this.aveRate + aveRate) / count;
        count++;
    }
}
