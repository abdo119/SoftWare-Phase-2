package com.SprintTwo.phaseTwo.controller;

import com.SprintTwo.phaseTwo.models.*;
import com.SprintTwo.phaseTwo.services.AdminServices;
import com.SprintTwo.phaseTwo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AdminServices adminServices;


    @PostMapping(value = "/login")
    long login(@RequestBody Admin admin) {
        return adminServices.login(admin.getEmail(), admin.getPassword());
    }

    @PostMapping(value = "/driversPending/{id}/{choice}")
    public Driver driversPending(@PathVariable int choice, @PathVariable long id) {
        return adminServices.driversPending(id, choice);
    }

    @PostMapping(value = "/suspendDriver/{id}/{action}")
    public Driver suspendDriver(@PathVariable long id, @PathVariable boolean action) {
        return adminServices.suspendDriver(id, action);
    }

    @PostMapping(value = "/suspendCustomer/{id}/{action}")
    public Customer suspendCustomer(@PathVariable long id, @PathVariable boolean action) {
        return adminServices.suspendCustomer(id, action);
    }

    @PostMapping(value = "/addDiscount/{id}/{discount}")
    public Ride addDiscount(@PathVariable long id, @PathVariable double discount) {
        return adminServices.addDiscount(id, discount);
    }
    @GetMapping(value = "/getCustomerEvents")
    public List<Events> getAllUserEvents() {
        return adminServices.getCustomerEvents();
    }
    @GetMapping(value = "/getDriversEvents")
    public List<Events> getDriverEvents() {
        return adminServices.getDriversEvents();
    }
    @GetMapping(value = "/getCustomer/{id}")
    public Customer getCustomer(@PathVariable long id) {
        return adminServices.getCustomer(id);
    }
    @GetMapping(value = "/getAllCustomers")
    public List<Customer> getCustomer() {
        return adminServices.getAllCustomer();
    }
    @GetMapping(value = "/getDriver/{id}")
    public Driver getDriver(@PathVariable long id) {
        return adminServices.getDriver(id);
    }
    @GetMapping(value = "/getAllDriver")
    public List<Driver> getAllDriver() {
        return adminServices.getAllDriver();
    }
    @PostMapping(value = "/updateRide/{id}")
    public Ride updateRide(@RequestBody Ride ride, @PathVariable long id){
        return adminServices.updateRide(ride,id);
    }
    @PostMapping(value = "/addPublicHoliday")
    public Holidays addHoliday(@RequestBody Holidays holiday){
       return adminServices.addHoliday(holiday);
    }
}
