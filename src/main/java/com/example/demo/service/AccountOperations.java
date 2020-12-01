package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.repository.AccountReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountOperations {

    private final AccountReactiveRepository repository;

    @Autowired
    public AccountOperations(AccountReactiveRepository repository) {
        this.repository = repository;
    }

    public Flux<Account> findAll() {
        return repository.findAll();
    }

    public Mono<Account> findById(String id) {
        return repository.findById(id);
    }

    public Mono<Account> save(Mono<Account> accountMono) {
        return accountMono.flatMap(repository::save);
    }
}
