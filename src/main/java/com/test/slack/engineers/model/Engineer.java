package com.test.slack.engineers.model;

public class Engineer {

    private String name;

    public Engineer() {}

    public Engineer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else if (this == o) {
            return true;
        } else if(!(o instanceof Engineer)) {
            return false;
        }
        Engineer engineer = (Engineer) o;
        return this.getName().equals(engineer.getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}
