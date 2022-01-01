package com.example.demo.models;

public class FavAreaDiscount extends DiscountDecorator{

    public FavAreaDiscount(InitialDiscount discount) {
        super(discount);
        this.discount.setDiscount(discount.getDiscount()+0.1);
    }
}
