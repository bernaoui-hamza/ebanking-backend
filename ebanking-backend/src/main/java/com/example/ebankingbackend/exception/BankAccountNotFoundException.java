package com.example.ebankingbackend.exception;

public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException(String message) {
    super(message);
    }
}
