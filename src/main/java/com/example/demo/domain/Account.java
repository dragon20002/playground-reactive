package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("accounts")
public class Account {

    @Id
    private String id;
    private long accountId;
    private long limit;
    private List<String> products;

    public Account() {
    }

    public Account(String id, long accountId, long limit, List<String> products) {
        this.id = id;
        this.accountId = accountId;
        this.limit = limit;
        this.products = products;
        if (this.products == null)
            this.products = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
