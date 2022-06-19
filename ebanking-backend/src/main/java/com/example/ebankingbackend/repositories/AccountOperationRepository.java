package com.example.ebankingbackend.repositories;
import com.example.ebankingbackend.entities.AccountOperation;
import com.example.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository <AccountOperation,Long>{
public List<AccountOperation> findByBankAccountId(String bankAccountId);
}