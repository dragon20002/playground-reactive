package com.example.demo.repository;

import com.example.demo.domain.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountReactiveRepository extends ReactiveMongoRepository<Account, String> {
    Flux<Account> findAllByLimit(long limit);

    Mono<Account> findFirstByAccountId(Mono<Long> accountId);
}
