package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.repositorys.DriversRepo;
import com.example.demo.repositorys.RidesRepo;
import com.example.demo.repositorys.SuccessfulRideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.repositorys.CustomerRepo;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CustomerServices {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RidesRepo ridesRepo;

    @Autowired
    DriverServices driverServices;

    @Autowired
    DriversRepo driversRepo;

    @Autowired
    CommonServices commonServices;

    @Autowired
    SuccessfulRideRepo successfulRideRepo;



    public Customer signup(Customer customer) {
        Customer cus = customerRepo.findByEmail(customer.getEmail());
        if (cus == null) {
            customer.setCheck(true);
            System.out.println("Your request is created successfully!");
            return customerRepo.save(customer);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This user is already found");
        }


    }

    public long login(String email, String password) {
        Customer customer = customerRepo.findByEmail(email);
        if (customer != null && Objects.equals(customer.getPassword(), password)) {
            return customer.getId();
        } else {
            System.out.println("Invalid email or password");
            return -1;
        }
    }

    public void bookRide(BookingDetails bookingDetails) {
        Ride ride = ridesRepo.getById(bookingDetails.getRideId());
        double discount = 0;
        Customer customer = customerRepo.getById(bookingDetails.getCustomerId());
        if(customer.getRideCounter()==1){
            discount = (10/100)*ride.getPrice();
        }
        else if(ride.getDiscount()!=0){
            discount = (10/100)*ride.getPrice();
        }
        else if(bookingDetails.getNumOfPassengers()>1){
            discount = (5/100)*ride.getPrice();
        }else if(Objects.equals(customer.getBirthDate(), LocalDate.now())){
            discount = (10/100)*ride.getPrice();
        }
        if(commonServices.withdraw(bookingDetails.getCustomerId(),1,ride.getPrice()-discount)){
            customer.setRideCounter(customer.getRideCounter()+1);
            customerRepo.save(customer);
            ride.setFlag(false);
            ride.setDestination(bookingDetails.getDestination());
            ride.setNumberOfPassenger(bookingDetails.getNumOfPassengers());
            ridesRepo.save(ride);
            Driver driver = ride.getDriver();
            commonServices.deposit(driver.getId(),2,ride.getPrice());
            Events event = new Events();
            event.setEventName("Customer accept Price");
            event.setEventTime(LocalDateTime.now());
            event.setCustomerName(customerRepo.getById(bookingDetails.getCustomerId()).getUsername());
            commonServices.putEvent(event);
            SuccessfulRide successfulRide = new SuccessfulRide();
            successfulRide.setRide(ride);
            driverServices.update(driver,bookingDetails.getRate(), successfulRideRepo.save(successfulRide).getId(),customerRepo.getById(bookingDetails.getCustomerId()).getUsername());

        }
    }
}
