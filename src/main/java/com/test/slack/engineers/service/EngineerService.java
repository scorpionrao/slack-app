package com.test.slack.engineers.service;

import com.test.slack.engineers.dao.EngineerDao;
import com.test.slack.engineers.model.Engineer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EngineerService {

    private final EngineerDao engineerDao;

    @Autowired
    public EngineerService(EngineerDao engineerDao) {
        this.engineerDao = engineerDao;
    }

    public Set<Engineer> getAllEngineers() {
        return this.engineerDao.getAllEngineers();
    }
}
