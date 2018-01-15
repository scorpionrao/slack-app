package com.test.slack.dao;

import com.test.slack.common.model.datasource.ExternalSource;
import com.test.slack.model.Engineers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class EngineerDao {

    private final ExternalSource externalSource;

    @Autowired
    public EngineerDao(ExternalSource externalSource) {
        this.externalSource = externalSource;
    }

    public Engineers getAllEngineers() throws Exception {
        CompletableFuture<Engineers> future = externalSource.getAllEngineers();
        return future.get();
    }
}
