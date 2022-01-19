package com.cry.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;


public class JobService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private int i;
    @Scheduled(cron = "* * * * * *")
    public void execute() {
        logger.info("thread id:{},FixedPrintTask execute times:{}", Thread.currentThread().getId(), ++i);
    }
}
