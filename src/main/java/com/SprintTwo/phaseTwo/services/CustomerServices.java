package com.SprintTwo.phaseTwo.services;

import com.SprintTwo.phaseTwo.models.*;
import com.SprintTwo.phaseTwo.repositorys.*;
import com.SprintTwo.phaseTwo.models.*;
import com.SprintTwo.phaseTwo.repositorys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    HolidayRepo holidayRepo;


    public Customer signup(Customer customer) {
        Customer cus = customerRepo.findByEmail(customer.getEmail());
        if (cus == null) {
            customer.setCheck(true);
            System.out.println("Your request is created successfully!");
            return customerRepo.save(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user is already found");
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
    private double calcDiscount(Customer customer , Ride ride, BookingDetails bookingDetails){
        DiscountDecorator discountDecorator = new DiscountDecorator(new InitialDiscount());
        if (customer.getRideCounter() == 1) {
            discountDecorator = new FirstRideDiscount(discountDecorator.getDiscount());
        }
        if (isPublicHoliday()) {
            discountDecorator = new PublicHoliday(discountDecorator.getDiscount());
        }
        if (ride.getDiscount() != 0) {
            discountDecorator = new FavAreaDiscount(discountDecorator.getDiscount());
        }
        if (bookingDetails.getNumOfPassengers() > 1) {
            discountDecorator = new PassengersDiscount(discountDecorator.getDiscount());
        }
        if (Objects.equals(customer.getBirthDate(), LocalDate.now())) {
            discountDecorator = new BirthDayDiscount(discountDecorator.getDiscount());
        }
        return discountDecorator.getDiscountValue() * ride.getPrice();
    }

    public void bookRide(BookingDetails bookingDetails) {
        Ride ride = ridesRepo.getById(bookingDetails.getRideId());
        Customer customer = customerRepo.getById(bookingDetails.getCustomerId());

        double discount = calcDiscount(customer,ride,bookingDetails);
        if (commonServices.withdraw(bookingDetails.getCustomerId(), 1, ride.getPrice() - discount)) {
            customer.setRideCounter(customer.getRideCounter() + 1);
            customerRepo.save(customer);
            ride.setFlag(false);
            ride.setDestination(bookingDetails.getDestination());
            ride.setNumberOfPassenger(bookingDetails.getNumOfPassengers());
            ridesRepo.save(ride);
            Driver driver = ride.getDriver();
            commonServices.deposit(driver.getId(), 2, ride.getPrice());
            Events event = new Events();
            event.setEventName("Customer accept Price");
            event.setEventTime(LocalDateTime.now());
            event.setCustomerName(customerRepo.getById(bookingDetails.getCustomerId()).getUsername());
            commonServices.putEvent(event);
            SuccessfulRide successfulRide = new SuccessfulRide();
            successfulRide.setRide(ride);
            driverServices.update(driver, bookingDetails.getRate(), successfulRideRepo.save(successfulRide).getId(), customerRepo.getById(bookingDetails.getCustomerId()).getUsername());

        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public Ride getRide(long id) {
        return ridesRepo.getById(id);
    }

    public List<Ride> getAllRideBySource(String source) {
        List<Ride> allRides = ridesRepo.findAll();
        List<Ride> rides = new ArrayList<>();
        for (Ride ride : allRides) {
            if (Objects.equals(ride.getSource(), source)) {
                rides.add(ride);
            }
        }
        return rides;
    }

    protected boolean isPublicHoliday() {
        List<Holidays> publicHolidays = holidayRepo.findAll();
        for (Holidays holiday : publicHolidays) {
            if (Objects.equals(holiday.getDate(), LocalDate.now())) {
                return true;
            }
        }
        return false;
    }
}
