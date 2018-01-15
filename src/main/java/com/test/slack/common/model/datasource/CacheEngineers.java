package com.test.slack.common.model.datasource;

import com.test.slack.model.Engineers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;

@Component
public class CacheEngineers implements ExternalSource {

    private Engineers engineers = null;
    private long lastRetrieved = System.currentTimeMillis();

    private final SQLiteSource sqLiteSource;

    @Autowired
    public CacheEngineers(SQLiteSource sqLiteSource) throws Exception {
        this.sqLiteSource = sqLiteSource;
        loadEngineersFromDB();
    }

    @Async
    private void loadEngineersFromDB() throws Exception {
        CompletableFuture<Engineers> future = this.sqLiteSource.getAllEngineers();
        engineers = future.get();
    }

    @Override
    public Engineers getAllEngineers() throws Exception {
        // load from DB every 10 seconds
        if(System.currentTimeMillis() >= lastRetrieved + 10000) {
            loadEngineersFromDB();
            lastRetrieved = System.currentTimeMillis();
        }
        return engineers;
    }
}
