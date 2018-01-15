package com.test.slack.common.model.datasource;

import com.test.slack.model.Engineers;

public interface ExternalSource {

    Engineers getAllEngineers() throws Exception;
}
