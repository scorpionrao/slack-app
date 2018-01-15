package com.test.slack.common.model.datasource;

import com.test.slack.model.Engineers;

import java.util.concurrent.CompletableFuture;

public interface ExternalSource {

    CompletableFuture<Engineers> getAllEngineers() throws Exception;
}
