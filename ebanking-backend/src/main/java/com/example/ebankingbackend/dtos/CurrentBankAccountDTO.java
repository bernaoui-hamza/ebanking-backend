package com.example.ebankingbackend.dtos;

import com.example.ebankingbackend.enumes.AccountStatus;
import lombok.Data;

import java.util.Date;


@Data
public  class CurrentBankAccountDTO extends BankAccountDTO {

    private String id;
    private double balance;
    private Date createdAt;
    private CustomerDTO customerDTO;
    private AccountStatus status;
    private  double overDaft;



}
