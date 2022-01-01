package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiscountDecorator {
    protected InitialDiscount discount;
    public DiscountDecorator(InitialDiscount discount){
        this.discount = discount;
    }
    public InitialDiscount getDiscount(){
        return discount;
    }
    public  double getDiscountValue(){return this.discount.getDiscount();}
}
