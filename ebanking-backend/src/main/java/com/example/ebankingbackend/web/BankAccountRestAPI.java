package com.example.ebankingbackend.web;

import com.example.ebankingbackend.Service.BankAccountService;
import com.example.ebankingbackend.dtos.AccountOperationDTO;
import com.example.ebankingbackend.dtos.BankAccountDTO;
import com.example.ebankingbackend.exception.BankAccountNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
@GetMapping("/account/{accountId}")
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
return bankAccountService.getBankAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccount(){
        return bankAccountService.bankAccountList();
    }
@GetMapping("/accounts/{accoun}")
    public List<AccountOperationDTO>getHistory(String accountId){
return bankAccountService.accountHistory(accountId);
    }
}
