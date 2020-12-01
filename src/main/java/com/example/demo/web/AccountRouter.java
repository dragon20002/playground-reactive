package com.example.demo.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AccountRouter {

    @Bean
    public RouterFunction<ServerResponse> routeByAccount(AccountHandler handler) {
        return nest(
                path("/api/accounts"),
                route(GET(""), handler::findAll)
                        .andRoute(GET("/{id}"), handler::findById)
                        .andRoute(POST("/"), handler::add)
        );
    }
}
