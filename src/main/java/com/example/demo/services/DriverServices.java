package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.repositorys.FavRepo;
import com.example.demo.repositorys.SuccessfulRideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.repositorys.DriversRepo;
import com.example.demo.repositorys.RidesRepo;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class DriverServices {


    @Autowired
    DriversRepo driversRepo;

    @Autowired
    RidesRepo ridesRepo;

    @Autowired
    FavRepo favRepo;

    @Autowired
    SuccessfulRideRepo successfulRideRepo;

    @Autowired
    CommonServices commonServices;

    public Driver addRide(DriverRequest driverRequest){
        Ride ride = new Ride();
        Events event = new Events();
        event.setPrice(driverRequest.getPrice());
        event.setEventTime(LocalDateTime.now());
        event.setCaptainName(driverRequest.getDriver().getUsername());
        event.setEventName("Captain put a Price");
        commonServices.putEvent(event);
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
    public Driver update(Driver driver,double rate,long rideId,String customerName){
        SuccessfulRide successfulRide = successfulRideRepo.getById(rideId);
        successfulRide.setRate(rate);
        successfulRideRepo.save(successfulRide);
        Driver newDriver = driversRepo.findByEmail(driver.getEmail());
        newDriver.getAcceptedRides().add(successfulRide);
        Events event = new Events("captainArrivedSource",LocalDateTime.now(),driver.getUsername(),customerName);
        commonServices.putEvent(event);
        event.setEventName("captainArrivedDest");
        event.setEventTime(LocalDateTime.now().plusMinutes(15));
        commonServices.putEvent(event);
        ridesRepo.delete(successfulRide.getRide());
        return driversRepo.save(newDriver);
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

    public void cancelRide(long rideId) {
        Ride ride = ridesRepo.getById(rideId);
        Driver driver = driversRepo.getById(ride.getDriver().getId());
        FavouriteAreas favouriteAreas = favRepo.findBySource(ride.getSource());
        driver.getFavouriteAreas().remove(favouriteAreas);
        favRepo.delete(favouriteAreas);
        ridesRepo.delete(ride);
        driversRepo.save(driver);
    }
}
