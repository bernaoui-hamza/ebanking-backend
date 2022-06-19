package com.example.ebankingbackend.dtos;

import java.util.List;

public class accountHistoryDTO {
    private String AccountId;
    private double balance;
    private String accountType;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<AccountOperationDTO> accountOperationDTOList;
}
