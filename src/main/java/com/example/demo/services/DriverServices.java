package com.example.demo.services;

import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.repositorys.DriversRepo;
import com.example.demo.repositorys.RidesRepo;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class DriverServices {


    @Autowired
    DriversRepo driversRepo;

    @Autowired
    RidesRepo ridesRepo;

    public Driver addRide(DriverRequest driverRequest){
        Ride ride = new Ride();
        ride.setSource(driverRequest.getSource());
        ride.setPrice(driverRequest.getPrice());
        ride.setDriver(driverRequest.getDriver());
        Driver driver = driversRepo.findByEmail(driverRequest.getDriver().getEmail());
        FavouriteAreas favouriteAreas = new FavouriteAreas();
        favouriteAreas.setSource(ride.getSource());
        favouriteAreas.setPrice(ride.getPrice());
        driver.getFavouriteAreas().add(favouriteAreas);
        ridesRepo.save(ride);
        return  driversRepo.save(driver);
    }
    public Driver update(Driver driver){
//        Driver oldDriver = driversRepo.findById(id).orElseThrow(()
//                -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
//        driver.setId(oldDriver.getId());
        return driversRepo.save(driver);
    }
    public Driver signup(Driver driver) {
        Driver temp = driversRepo.findByEmail(driver.getEmail());
        if(temp == null){
            driver.setCheck(false);
            System.out.println("Your request is pending...");
            return driversRepo.save(driver);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This user is already found");
        }

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
