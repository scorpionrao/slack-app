package com.test.slack.service;

import com.test.slack.common.model.datasource.ExternalSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final ExternalSource externalSource;

    @Autowired
    public AppRunner(ExternalSource externalSource) {
        this.externalSource = externalSource;
    }

    @Override
    public void run(String... args) throws Exception {

        long start = System.currentTimeMillis();
        for(int i = 0; i < 1; i++) {
            externalSource.getAllEngineers();
        }
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
    }
}
