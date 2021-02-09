package com.wakaka.server1.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.context.annotation.Primary;


public class SeataAutoConfig {
    private final static Logger logger = LoggerFactory.getLogger(SeataAutoConfig.class);

    /**
     * init global transaction scanner
     *
     * @Return: GlobalTransactionScanner
     */


}
