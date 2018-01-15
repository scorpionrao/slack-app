package com.test.slack.common.model.datasource;

import com.test.slack.engineers.model.Engineer;

import java.util.Set;

public interface ExternalSource {

    Set<Engineer> getAllEngineers();
}
