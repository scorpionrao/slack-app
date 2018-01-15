package com.test.slack.common.model.datasource;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public abstract class SQLLiteSourceBase {

    protected Connection connect() throws SQLException {
        String url = "jdbc:sqlite:deploys.sqlite";
        return DriverManager.getConnection(url);
    }

}
