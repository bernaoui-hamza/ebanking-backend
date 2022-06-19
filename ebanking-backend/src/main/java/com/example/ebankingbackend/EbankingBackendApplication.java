package com.example.ebankingbackend;

import com.example.ebankingbackend.Service.BankAccountService;
import com.example.ebankingbackend.Service.BankService;
import com.example.ebankingbackend.dtos.BankAccountDTO;
import com.example.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.dtos.SavingBankAccountDTO;
import com.example.ebankingbackend.entities.*;
import com.example.ebankingbackend.enumes.AccountStatus;
import com.example.ebankingbackend.enumes.OperationType;
import com.example.ebankingbackend.exception.BalenceNotSufficientException;
import com.example.ebankingbackend.exception.BankAccountNotFoundException;
import com.example.ebankingbackend.exception.CustomerNotFound;
import com.example.ebankingbackend.repositories.AccountOperationRepository;
import com.example.ebankingbackend.repositories.BankAccountRepository;
import com.example.ebankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;


import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication


public class
EbankingBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(EbankingBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService)
    {
        return args->{
//bankService.consulter();
            Stream.of("hamza","Bernaoui","Ikram")
                    .forEach(name->{
                        CustomerDTO customerDTO=new CustomerDTO();
                        customerDTO.setName(name);
                        customerDTO.setEmail(name+"@gmail.com");
                        bankAccountService.saveCustomer(customerDTO);

                    });
            bankAccountService.listeCustomer().forEach(cust->{
                try {
                    bankAccountService.saveCurrentBankAccount
                            (Math.random()*9000,9000,cust.getId());
                    bankAccountService.saveSavingBankAccount
                            (Math.random()*12000,5.5,cust.getId());

                } catch (CustomerNotFound e) {
                    e.printStackTrace();
                }

            } );
            List<BankAccountDTO> bankAccounts=bankAccountService.bankAccountList();
            for(BankAccountDTO bankAccount:bankAccounts){
                for(int i=0;i<10;i++){
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();

                    }else{
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();

                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"credit");
                    bankAccountService.debit(accountId,1000+Math.random()*9000,"debit");

                }

            }
        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository ,
                            BankAccountRepository bankAccountRepository
                              , AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("Hassan", "Yassine").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());

                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(acc -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 12000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }

            });

        };
    }

}
