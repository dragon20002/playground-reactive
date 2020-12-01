package com.example.demo.web;

import com.example.demo.domain.Account;
import com.example.demo.service.AccountOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Configuration
public class AccountHandler {
    private static final Logger log = LoggerFactory.getLogger(AccountHandler.class);

    private final AccountOperations service;

    public AccountHandler(AccountOperations service) {
        this.service = service;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(service.findAll(), Account.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        try {
            return ServerResponse.ok().body(service.findById(id), Account.class);
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().build();
        }
    }

    public Mono<ServerResponse> add(ServerRequest request) {
        Mono<Account> accountMono = request.bodyToMono(Account.class)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect body data")));
        return ServerResponse.ok().body(service.save(accountMono), Account.class);
    }

}
