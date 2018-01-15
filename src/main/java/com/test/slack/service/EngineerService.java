package com.test.slack.service;

import com.test.slack.dao.EngineerDao;
import com.test.slack.model.Engineer;
import com.test.slack.model.Engineers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class EngineerService {

    Logger logger = LoggerFactory.getLogger(EngineerService.class);
    private final EngineerDao engineerDao;

    @Autowired
    public EngineerService(EngineerDao engineerDao) {
        this.engineerDao = engineerDao;
    }

    public Engineers getAllEngineers() throws Exception {
        return engineerDao.getAllEngineers();
    }

}
