package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.repositorys.CustomerRepo;
import org.springframework.web.server.ResponseStatusException;


import java.util.Objects;

@Service
public class CustomerServices {

    @Autowired
    CustomerRepo customerRepo;

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
}
