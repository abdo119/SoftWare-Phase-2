package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.repositorys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    EventsRepo eventsRepo;


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
    public List<Events> getCustomerEvents(){
        List <Events> allEvents = eventsRepo.findAll();
        List <Events> userEvents = new ArrayList<>();

        for(Events event: allEvents){
            if(Objects.equals(event.getEventName(), "Customer accept Price")){
                userEvents.add(event);
            }
        }
        return userEvents;
    }
    public List<Events> getDriversEvents(){
        List <Events> allEvents = eventsRepo.findAll();
        List <Events> DriversEvents = new ArrayList<>();

        for(Events event: allEvents){
            if(!Objects.equals(event.getEventName(), "Customer accept Price")){
                DriversEvents.add(event);
            }
        }
        return DriversEvents;
    }
    public Customer getCustomer(long id){
        return customerRepo.getById(id);
    }
    public List<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }
    public Driver getDriver(long id){
        return driversRepo.getById(id);
    }
    public List<Driver> getAllDriver(){
        return driversRepo.findAll();
    }
    public Ride updateRide(Ride ride,long id){
        Ride oldRide = ridesRepo.getById(id);
        ride.setId(oldRide.getId());
        return ridesRepo.save(ride);
    }
}
