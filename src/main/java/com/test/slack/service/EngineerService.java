package com.test.slack.service;

import com.test.slack.dao.EngineerDao;
import com.test.slack.model.Engineers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineerService {

    private final EngineerDao engineerDao;

    @Autowired
    public EngineerService(EngineerDao engineerDao) {
        this.engineerDao = engineerDao;
    }

    public Engineers getAllEngineers() throws Exception {
        return engineerDao.getAllEngineers();
    }

}
