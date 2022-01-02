package com.SprintTwo.phaseTwo.models;

public class PublicHoliday extends DiscountDecorator{
    public PublicHoliday(InitialDiscount discount) {
        super(discount);

        this.discount.setDiscount(discount.getDiscount()+0.05);
    }
}
