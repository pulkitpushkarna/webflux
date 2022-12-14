package com.reactive.webflux.webflux.controller;


import com.reactive.webflux.webflux.dao.CustomerDao;
import com.reactive.webflux.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @GetMapping("/")
    public List<Customer> getCustomers(){
        return customerDao.getCustomers();
    }

    @GetMapping("/flux")
    public Flux<Customer> getCustomersFlux(){
        return customerDao.getCustomerStream();
    }


    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersStream(){
        return customerDao.getCustomerStream();
    }


}
