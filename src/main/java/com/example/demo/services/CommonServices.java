package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.Driver;
import com.example.demo.models.Events;
import com.example.demo.repositorys.CustomerRepo;
import com.example.demo.repositorys.DriversRepo;
import com.example.demo.repositorys.EventsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommonServices {

    @Autowired
    EventsRepo eventsRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    DriversRepo driversRepo;

    public Events putEvent(Events events) {
        return eventsRepo.save(events);
    }

    public double deposit(long id, int type, double amount) {
        if (type == 1) {
            Customer customer = customerRepo.getById(id);
            customer.setBalance(customer.getBalance() + amount);
            return customerRepo.save(customer).getBalance();
        } else if (type == 2) {
            Driver driver = driversRepo.getById(id);
            driver.setBalance(driver.getBalance() + amount);
            return driversRepo.save(driver).getBalance();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean withdraw(long id, int type, double amount) {
        if (type == 1) {
            Customer customer = customerRepo.getById(id);
            if (customer.getBalance() >= amount) {
                customer.setBalance(customer.getBalance() - amount);
                customerRepo.save(customer).getBalance();
                return true;
            } else {
                return false;
            }
        } else if (type == 2) {
            Driver driver = driversRepo.getById(id);
            if (driver.getBalance() >= amount) {
                driver.setBalance(driver.getBalance() - amount);
                driversRepo.save(driver).getBalance();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


}
