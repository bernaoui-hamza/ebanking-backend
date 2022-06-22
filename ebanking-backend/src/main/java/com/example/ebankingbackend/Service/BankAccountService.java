package com.example.ebankingbackend.Service;

import com.example.ebankingbackend.dtos.*;
import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.CurrentAccount;
import com.example.ebankingbackend.entities.Customer;
import com.example.ebankingbackend.entities.SavingAccount;
import com.example.ebankingbackend.exception.BalenceNotSufficientException;
import com.example.ebankingbackend.exception.BankAccountNotFoundException;
import com.example.ebankingbackend.exception.CustomerNotFound;

import java.util.List;

public interface  BankAccountService {


     CustomerDTO saveCustomer(CustomerDTO customerDTO);

     CurrentBankAccountDTO saveCurrentBankAccount
             (double initialBanlance, double overDraft, Long cutomerId) throws CustomerNotFound;
     SavingBankAccountDTO saveSavingBankAccount
             (double initialBanlance, double intrestRate, Long cutomerId) throws CustomerNotFound;

     List<CustomerDTO> listeCustomer();

     BankAccountDTO getBankAccount(String accountId)
             throws BankAccountNotFoundException;
     void debit(String accountId,double amount,String description) throws BankAccountNotFoundException, BalenceNotSufficientException;
     void credit(String accountId,double amount,String description) throws BankAccountNotFoundException;
     void  transfer(String accountIdSource,String accountIdDestination,double amount) throws BankAccountNotFoundException, BalenceNotSufficientException;

     List<BankAccountDTO> bankAccountList();

     CustomerDTO getCustomerDTO(Long customerId) throws CustomerNotFound;

     CustomerDTO getCustomer(Long customerId) throws CustomerNotFound;

     CustomerDTO updateCustomer(CustomerDTO customerDTO);

     void deleteCustomer(Long customerId);

     List<AccountOperationDTO> accountHistory(String AccountId);

     AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
