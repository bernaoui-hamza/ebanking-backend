package com.example.ebankingbackend.dtos;

import com.example.ebankingbackend.entities.AccountOperation;
import com.example.ebankingbackend.entities.Customer;
import com.example.ebankingbackend.enumes.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;



import java.util.Date;



@Data
public  class SavingBankAccountDTO extends BankAccountDTO {

    private String id;
    private double balance;
    private Date createdAt;
    private CustomerDTO customerDTO;
    private AccountStatus status;
    private  double interestRate;



}
