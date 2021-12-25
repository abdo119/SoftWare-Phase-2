package com.example.demo.controller;

import com.example.demo.models.*;
import com.example.demo.repositorys.DriversRepo;
import com.example.demo.services.CommonServices;
import com.example.demo.services.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {
    @Autowired
    DriverServices driverServices;

    @Autowired
    DriversRepo driversRepo;

    @Autowired
    CommonServices commonServices;
    @PostMapping(value = "/login")
    long login(@RequestBody Driver driver) throws Exception {

        return driverServices.login(driver.getEmail(), driver.getPassword());
    }
    @PostMapping(value = "/signUp")
    Driver signUp(@RequestBody Driver driver) throws Exception {

        return driverServices.signup(driver);
    }

    @PostMapping(value = "/addRide")
    Driver addRide(@RequestBody DriverRequest driver) throws Exception {
        return driverServices.addRide(driver);
    }

    @PostMapping(value = "/putPrice")
    Events putPrice(@RequestBody Events events){
        events.setEventName("Captain put a Price");
        return commonServices.putEvent(events);
    }
    @PostMapping(value = "/captainArrivedSource")
    Events captainArrivedSource(@RequestBody Events events){
        events.setEventName("Captain has arrived Location");
        return commonServices.putEvent(events);
    }
    @PostMapping(value = "/captainArrivedDest")
    Events captainArrivedDest(@RequestBody Events events){
        events.setEventName("Captain has arrived Destination");
        return commonServices.putEvent(events);
    }
    @PostMapping(value = "/withdraw/{id}/{type}/{amount}")
    public boolean withdraw(@PathVariable long id,@PathVariable int type,@PathVariable double amount){
        return commonServices.withdraw(id,type,amount);
    }
    @PostMapping(value = "/deposit/{id}/{type}/{amount}")
    public double deposit(@PathVariable long id,@PathVariable int type,@PathVariable double amount){
        return commonServices.deposit(id,type,amount);
    }



}
