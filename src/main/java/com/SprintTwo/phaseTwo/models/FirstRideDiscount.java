package com.SprintTwo.phaseTwo.models;

public class FirstRideDiscount extends DiscountDecorator {

    public FirstRideDiscount(InitialDiscount discount) {
        super(discount);
        this.discount.setDiscount(discount.getDiscount()+0.1);
    }

}
