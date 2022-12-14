package com.reactive.webflux.webflux.event;


import com.reactive.webflux.webflux.entity.Product;
import com.reactive.webflux.webflux.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("application started..");

        productRepository.count().subscribe(count->{
            if(count==0){
                Flux.range(1,10)
                        .delayElements(Duration.ofSeconds(1))
                        .map(e->new Product("Product"+e))
                        .subscribe(product ->
                                productRepository.save(product)
                                        .subscribe(System.out::println)
                        );
            }
        });
    }
}
