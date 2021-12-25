package com.example.demo.services;

import com.example.demo.models.Events;
import com.example.demo.repositorys.EventsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServices {

    @Autowired
    EventsRepo eventsRepo;
    public Events putEvent(Events events){
        return eventsRepo.save(events);
    }
}
