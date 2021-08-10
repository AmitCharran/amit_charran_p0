package com.revature.presentation;

import com.revature.service.*;

public interface BankClassPresentation {
    AccountService as = new AccountServiceImpl();
    ClientService cs = new ClientServiceImpl();
    HolderService hs = new HolderServiceImpl();
    TransactionService ts = new TransactionServiceImpl();

    // work on this later for proper output




}


