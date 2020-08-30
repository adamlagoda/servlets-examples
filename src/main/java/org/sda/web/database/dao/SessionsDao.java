package org.sda.web.database.dao;

import org.sda.web.database.configuration.DatasourceConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionsDao {

    private DatasourceConfiguration datasourceConfiguration;

    public SessionsDao() {
        datasourceConfiguration = DatasourceConfiguration.getInstance();
    }

    public void save(String adminLogin, long sessionId, long token) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement insertSession = connection.prepareStatement("INSERT INTO sessions (session_id, token) VALUES (?, ?)");
             PreparedStatement updateAdmins = connection.prepareStatement("UPDATE admins SET session_id = ? WHERE login = ?")) {
            insertSession.setLong(1, sessionId);
            insertSession.setLong(2, token);
            if (insertSession.executeUpdate() <= 0) {
                throw new SQLException("Couldn't insert new session");
            }
            updateAdmins.setLong(1, sessionId);
            updateAdmins.setString(2, adminLogin);
            if (updateAdmins.executeUpdate() <= 0) {
                throw new SQLException("Couldn't update admins");
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

    public boolean hasSession(long sessionId) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM sessions WHERE session_id=?")) {
            statement.setLong(1, sessionId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
