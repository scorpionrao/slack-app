package com.test.slack.common.model.datasource;

import com.test.slack.engineers.model.Engineer;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@Repository
public class SQLiteSource extends SQLLiteSourceBase implements ExternalSource {

    /**
     * select all engineers in the deploys table
     */
    public Set<Engineer> getAllEngineers() {

        String sql = "SELECT distinct(engineer) FROM deploys";

        Connection conn = null;
        try {
            conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            Set<Engineer> engineers = new HashSet<>();
            while (rs.next()) {
                String name = rs.getString("engineer");
                engineers.add(new Engineer(name));
             }
             return engineers;
        } catch (SQLException e) {
            throw new RuntimeException("Error Retrieving - All engineers");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error Retrieving - All engineers");
                }
            }
        }
    }

    public static void main(String[] args) {
        SQLiteSource app = new SQLiteSource();
        app.getAllEngineers();
    }
}
