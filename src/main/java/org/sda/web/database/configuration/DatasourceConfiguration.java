package org.sda.web.database.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatasourceConfiguration {

    MysqlDataSource dataSource;

    public DatasourceConfiguration() {
        configure();
    }

    public void configure() {
        try {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("servers");
            dataSource.setPort(3306);
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setServerTimezone("Europe/Warsaw");
            dataSource.setCharacterEncoding("UTF-8");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
