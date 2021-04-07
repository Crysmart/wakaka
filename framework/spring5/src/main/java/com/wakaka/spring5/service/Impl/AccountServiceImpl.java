package com.wakaka.spring5.service.Impl;

import com.wakaka.spring5.entity.Account;
import com.wakaka.spring5.service.AccountService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Crysmart
 * @date 2021/4/7 14:25
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Mono<Account> getAccount() {
        Account account = new Account();
        account.setName("name");
        account.setPwd("pwd");
        return Mono.just(account);
    }

    @Override
    public Flux<Account> getAccountList() {
        List<Account> accountList = new ArrayList<>();

        accountList.add(new Account("a1","p1"));
        accountList.add(new Account("a22","p22"));

        return Flux.fromIterable(accountList);
    }
}
