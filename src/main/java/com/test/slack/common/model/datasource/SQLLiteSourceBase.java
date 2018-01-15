package com.test.slack.common.model.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public abstract class SQLLiteSourceBase {

    private static final Logger logger = LoggerFactory.getLogger(SQLLiteSourceBase.class);

    protected Connection connect() {
        String url = "jdbc:sqlite:deploys.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return conn;
    }

}
