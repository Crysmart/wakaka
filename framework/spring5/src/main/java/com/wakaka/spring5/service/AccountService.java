package com.wakaka.spring5.service;

import com.wakaka.spring5.entity.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Crysmart
 * @date 2021/4/7 14:25
 */
public interface AccountService {
    Mono<Account> getAccount();

    Flux<Account> getAccountList();
}
