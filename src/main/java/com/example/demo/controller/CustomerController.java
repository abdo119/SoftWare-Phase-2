package com.example.demo.controller;
import com.example.demo.models.Customer;
import com.example.demo.models.Events;
import com.example.demo.repositorys.DriversRepo;
import com.example.demo.services.CommonServices;
import com.example.demo.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    CustomerServices customerServices;

    @Autowired
    DriversRepo driversRepo;

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
    Events acceptPrice(@RequestBody Events events){
        events.setEventName("Customer accept Price");
        return commonServices.putEvent(events);
    }
}
