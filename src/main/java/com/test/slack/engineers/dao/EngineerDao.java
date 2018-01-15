package com.test.slack.engineers.dao;

import com.test.slack.common.model.datasource.ExternalSource;
import com.test.slack.engineers.model.Engineer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EngineerDao {

    private final ExternalSource externalSource;

    @Autowired
    public EngineerDao(ExternalSource externalSource) {
        this.externalSource = externalSource;
    }

    public Set<Engineer> getAllEngineers() {
        return this.externalSource.getAllEngineers();
    }
}
