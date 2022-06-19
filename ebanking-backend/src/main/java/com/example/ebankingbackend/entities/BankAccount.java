package com.example.ebankingbackend.entities;

import com.example.ebankingbackend.enumes.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 4)
@Data @NoArgsConstructor @AllArgsConstructor
public  class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
     @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;


}
