package com.SprintTwo.phaseTwo.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingDetails {
    protected String destination;
    protected long rideId;
    protected double rate;
    protected long customerId;
    protected int numOfPassengers;
}
