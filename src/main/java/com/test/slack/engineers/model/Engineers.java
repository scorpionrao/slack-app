package com.test.slack.engineers.model;

import java.util.HashSet;
import java.util.Set;

public class Engineers {

    private Set<Engineer> engineers = new HashSet<>();

    public Engineers() {}

    public Engineers(Set<Engineer> engineers) {
        setEngineers(engineers);
    }

    public Set<Engineer> getEngineers() {
        return engineers;
    }

    public void setEngineers(Set<Engineer> engineers) {
        this.engineers = engineers;
    }

}
