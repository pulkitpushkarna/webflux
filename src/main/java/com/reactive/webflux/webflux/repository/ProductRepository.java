package com.reactive.webflux.webflux.repository;

import com.reactive.webflux.webflux.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
