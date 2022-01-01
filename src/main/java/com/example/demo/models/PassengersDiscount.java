package com.example.demo.models;

public class PassengersDiscount extends DiscountDecorator{

    public PassengersDiscount(InitialDiscount discount) {
        super(discount);
        this.discount.setDiscount(discount.getDiscount()+0.05);
    }
}
