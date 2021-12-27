package com.example.demo.services;

import com.example.demo.models.Admin;
import com.example.demo.models.Customer;
import com.example.demo.models.Driver;
import com.example.demo.models.Ride;
import com.example.demo.repositorys.AdminRepo;
import com.example.demo.repositorys.CustomerRepo;
import com.example.demo.repositorys.DriversRepo;
import com.example.demo.repositorys.RidesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AdminServices {
    @Autowired
    AdminRepo adminRepo;

    @Autowired
    DriversRepo driversRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RidesRepo ridesRepo;

    public long login(String email, String password) {
        Admin admin = adminRepo.findByEmail(email);
        if (admin != null && Objects.equals(admin.getPassword(), password)) {
            return admin.getId();
        } else {
            System.out.println("Invalid email or password");
            return -1;
        }
    }

    public Driver driversPending(long id, int choice) {
        Driver driver = driversRepo.getById(id);
        if (choice == 1) {
            driver.setCheck(true);
            return driversRepo.save(driver);
        } else if (choice == 2) {
            driversRepo.delete(driver);
            throw new ResponseStatusException(HttpStatus.ACCEPTED, "Deleted");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Driver suspendDriver(long id, boolean action) {
        Driver driver = driversRepo.getById(id);
        driver.setCheck(action);
        return driversRepo.save(driver);
    }

    public Customer suspendCustomer(long id, boolean action) {
        Customer customer = customerRepo.getById(id);
        customer.setCheck(action);
        return customerRepo.save(customer);
    }

    public Ride addDiscount(long id, double discount) {
        Ride ride = ridesRepo.getById(id);
        ride.setDiscount(discount);
        return ridesRepo.save(ride);
    }
}
