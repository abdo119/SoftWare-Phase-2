package com.example.demo.services;

import com.example.demo.models.Driver;
import com.example.demo.models.Ride;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositorys.DriversRepo;
import com.example.demo.repositorys.RidesRepo;

import java.util.Objects;

@Service
public class DriverServices {


    @Autowired
    DriversRepo driversRepo;

    @Autowired
    RidesRepo ridesRepo;

    public void addRide(String source, Double price, Driver driver ){
        Ride ride = new Ride();
        ride.setSource(source);
        ride.setPrice(price);
        ride.setDriver(driver);
        driver.getFavouriteAreas().add(ride);
        ridesRepo.save(ride);
    }
    public Driver update(Driver driver){
//        Driver oldDriver = driversRepo.findById(id).orElseThrow(()
//                -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
//        driver.setId(oldDriver.getId());
        return driversRepo.save(driver);
    }
    public Driver signup(Driver driver) {

        driver.setCheck(false);
        System.out.println("Your request is pending...");
        return driversRepo.save(driver);
    }
    public long login(String email, String password) {
        Driver driver = driversRepo.findByEmail(email);
        if(driver!=null && Objects.equals(driver.getPassword(), password)){
            return driver.getId();
        }else {
            System.out.println("Invalid email or password");
            return -1;
        }
    }
}
