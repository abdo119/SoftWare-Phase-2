package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositorys.CustomerRepo;


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
            System.out.println("This user is already found!!");
            return null;
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
