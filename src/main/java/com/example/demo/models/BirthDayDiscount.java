package com.example.demo.models;

public class BirthDayDiscount extends DiscountDecorator {
    public BirthDayDiscount(InitialDiscount discount) {
        super(discount);
        this.discount.setDiscount(discount.getDiscount()+0.1);
    }
}
