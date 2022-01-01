package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InitialDiscount {
    private double discount;
    public InitialDiscount(){
        this.discount = 0.0;
    }
}
