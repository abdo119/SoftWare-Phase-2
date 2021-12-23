package com.example.demo.services;

import com.example.demo.models.Driver;
import com.example.demo.models.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositorys.RidesRepo;

@Service
public class RideServices {
    @Autowired
    RidesRepo ridesRepo;

    @Autowired
    DriverServices driverServices;

    public void bookRide(String destination,Long rideId,double rate) {
        Ride ride = ridesRepo.getById(rideId);
        ride.setDestination(destination);
        Driver driver = ride.getDriver();
        driver.setAveRate(rate);
        driver.getFavouriteAreas().add(ride);
        driverServices.update(driver);
        ridesRepo.delete(ride);
    }

    public void cancelRide(long rideId) {
        Ride ride = ridesRepo.getById(rideId);
        ridesRepo.delete(ride);

    }
}
