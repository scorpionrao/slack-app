package com.test.slack.common.model.datasource;

import com.test.slack.model.Engineer;
import com.test.slack.model.Engineers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Component
public class SQLiteSource extends SQLLiteSourceBase {

    private static final Logger logger = LoggerFactory.getLogger(SQLiteSource.class);

    public static final String QUERY_ALL_ENGINEERS = "SELECT distinct(engineer) FROM deploys";

    /**
     * select all engineers in the deploys table
     */
    public CompletableFuture<Engineers> getAllEngineers() throws Exception {

        Connection conn = null;
        try {
            conn = connect();
            logger.info(Thread.currentThread().getName());
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(QUERY_ALL_ENGINEERS);
            Set<Engineer> engineerSet = new HashSet<>();
            while (resultSet.next()) {
                String name = resultSet.getString("engineer");
                engineerSet.add(new Engineer(name));
            }
            Engineers engineers = new Engineers(engineerSet);
            return CompletableFuture.completedFuture(engineers);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
