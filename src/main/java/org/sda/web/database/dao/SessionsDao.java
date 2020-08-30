package org.sda.web.database.dao;

import org.sda.web.database.configuration.DatasourceConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessionsDao {

    private DatasourceConfiguration datasourceConfiguration;

    public SessionsDao() {
        datasourceConfiguration = DatasourceConfiguration.getInstance();
    }

    public void save(long sessionId, long token) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO sessions (session_id, token) VALUES (?, ?)")) {
            statement.setLong(1, sessionId);
            statement.setLong(2, token);
            if (statement.executeUpdate() <= 0) {
                throw new SQLException("Couldn't insert new session");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(long sessionId) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM sessions WHERE session_id=?")) {
            statement.setLong(1, sessionId);
            if (statement.executeUpdate() <= 0) {
                throw new SQLException("Couldn't delete session");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
