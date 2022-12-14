package com.reactive.webflux.webflux.dao;

import com.reactive.webflux.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(e -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    return new Customer(e, "Customer" + e);
                }).collect(Collectors.toList());

    }

    public Flux<Customer> getCustomerStream() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .map(e -> new Customer(e, "Cutomer" + e));
    }

}
