package com.example.demo.repository;

import com.example.demo.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class AccountReactiveRepositoryTest {

    @Autowired
    private ReactiveMongoTemplate template;

    @Autowired
    private AccountReactiveRepository repository;

    @BeforeEach
    public void beforeEach() {
        template.dropCollection(Account.class).subscribe();
    }

    @Test
    public void givenLimit_whenFindAllByLimit_thenFindAccount() {
        repository.save(new Account(null, 1L, 1000L, null)).block();
        Flux<Account> accountFlux = repository.findAllByLimit(1000L);

        StepVerifier
                .create(accountFlux)
                .assertNext(account -> {
                    assertEquals(1L, account.getAccountId());
                    assertEquals(1000L, account.getLimit());
                    assertNotNull(account.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void givenAccountId_whenFindFirstByAccountId_thenFindAccount() {
        repository.save(new Account(null, 2L, 2000L, null)).block();
        Mono<Account> accountMono = repository
                .findFirstByAccountId(Mono.just(2L));

        StepVerifier
                .create(accountMono)
                .assertNext(account -> {
                    assertEquals(2L, account.getAccountId());
                    assertEquals(2000L, account.getLimit());
                    assertNotNull(account.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void givenAccount_whenSave_thenSaveAccount() {
        Mono<Account> accountMono = repository.save(new Account(null, 3L, 3000L, null));

        StepVerifier
                .create(accountMono)
                .assertNext(account -> assertNotNull(account.getId()))
                .expectComplete()
                .verify();
    }
}
