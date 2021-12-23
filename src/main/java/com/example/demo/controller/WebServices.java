package com.example.demo.controller;

import com.example.demo.models.Admin;
import com.example.demo.models.Driver;
import com.example.demo.models.Person;
import com.example.demo.models.User;
import com.example.demo.repositorys.DriversRepo;
import com.example.demo.services.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositorys.AdminRepo;

@RestController
public class WebServices {
    @Autowired
    DriverServices driverServices;

    @Autowired
    DriversRepo driversRepo;
    @PostMapping(value = "/login")
    Driver login(@RequestBody Driver user) throws Exception {

        return driverServices.signup(user);
    }
    @GetMapping(value = "/l")
    Driver getUser() throws Exception {

        return driversRepo.getById(1L) ;
    }

}
