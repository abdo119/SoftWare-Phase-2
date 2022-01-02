package com.SprintTwo.phaseTwo.controller;

import com.SprintTwo.phaseTwo.models.BookingDetails;
import com.SprintTwo.phaseTwo.models.Customer;
import com.SprintTwo.phaseTwo.models.Events;
import com.SprintTwo.phaseTwo.models.Ride;
import com.SprintTwo.phaseTwo.services.CommonServices;
import com.SprintTwo.phaseTwo.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    CustomerServices customerServices;

    @Autowired
    CommonServices commonServices;

    @PostMapping(value = "/login")
    long login(@RequestBody Customer customer) throws Exception {

        return customerServices.login(customer.getEmail(), customer.getPassword());
    }

    @PostMapping(value = "/signUp")
    Customer signUp(@RequestBody Customer customer) throws Exception {
        return customerServices.signup(customer);
    }

    @PostMapping(value = "/acceptPrice")
    Events acceptPrice(@RequestBody Events events) {
        events.setEventName("Customer accept Price");
        return commonServices.putEvent(events);
    }

    @PostMapping(value = "/withdraw/{id}/{type}/{amount}")
    public boolean withdraw(@PathVariable long id, @PathVariable int type, @PathVariable double amount) {
        return commonServices.withdraw(id, type, amount);
    }

    @PostMapping(value = "/deposit/{id}/{type}/{amount}")
    public double deposit(@PathVariable long id, @PathVariable int type, @PathVariable double amount) {
        return commonServices.deposit(id, type, amount);
    }

    @PostMapping(value = "/bookRide")
    void bookRide(@RequestBody BookingDetails bookingDetails) {
        customerServices.bookRide(bookingDetails);
    }

    @GetMapping (value = "/getRidesBySource")
    List<Ride> getAllRidesBySource(@RequestParam(name = "source") String source){
        return customerServices.getAllRideBySource(source);
    }
    @GetMapping (value = "/getRidesBySource/{id}")
    Ride getAllRidesBySource(@PathVariable long id){
        return customerServices.getRide(id);
    }
}
