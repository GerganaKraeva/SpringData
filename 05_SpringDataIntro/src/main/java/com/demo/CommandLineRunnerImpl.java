package com.demo;

import com.demo.data.entities.Account;
import com.demo.data.entities.User;
import com.demo.service.AccountService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private final UserService userService;

    @Autowired
    private final AccountService accountService;


    public CommandLineRunnerImpl(UserService userService, AccountService accountService) {

        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = this.userService.findUserById(1);
        User user2 = this.userService.findUserById(2);
        this.accountService.withdrawMoney(BigDecimal.valueOf(50), 1);
        this.accountService.transferMoney(BigDecimal.valueOf(1000), 2);
        this.userService.findByName("Ani");
    }
}
