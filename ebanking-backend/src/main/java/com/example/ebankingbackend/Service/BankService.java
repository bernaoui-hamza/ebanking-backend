package com.example.ebankingbackend.Service;

import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.CurrentAccount;
import com.example.ebankingbackend.entities.SavingAccount;
import com.example.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount=
                bankAccountRepository.findById("059bca13-4714-4f74-9a61-1c4da8f07fc9").orElse(null);
        System.out.println(bankAccount.getId());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getCreatedAt());
        System.out.println(bankAccount.getCustomer().getName());
        System.out.println(bankAccount.getClass().getSimpleName());
        if(bankAccount instanceof SavingAccount)
            System.out.println(((SavingAccount) bankAccount).getInterestRate());
        else  if(bankAccount instanceof CurrentAccount)
            System.out.println(((CurrentAccount) bankAccount).getOverDraft());
        bankAccount.getAccountOperations().forEach(op->{
            System.out.println(op.getType()+"\t"+op.getOperationDate()+"\t"+op.getAmount());
        });
    }
}
