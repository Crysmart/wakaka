package com.wakaka.spring5.controller;

import com.wakaka.spring5.entity.Account;
import com.wakaka.spring5.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Crysmart
 * @date 2021/4/7 14:23
 */
@RestController
public class AccountController {
    static Integer count = 0;

    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    public Mono<Account> getAccount(){
        count++;
        System.out.println(count);
        Mono<Account> account = accountService.getAccount();
        return account;
    }

    @GetMapping("/accountList")
    public Flux<Account> getAccountList(){
        Flux<Account> accountList = accountService.getAccountList();
        return accountList;
    }
}
