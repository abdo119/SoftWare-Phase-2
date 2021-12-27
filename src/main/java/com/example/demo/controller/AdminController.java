package com.example.demo.controller;

import com.example.demo.models.Admin;
import com.example.demo.models.Customer;
import com.example.demo.models.Driver;
import com.example.demo.models.Ride;
import com.example.demo.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
