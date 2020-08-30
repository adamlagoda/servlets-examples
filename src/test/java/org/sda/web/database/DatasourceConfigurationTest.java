package org.sda.web.database;

import org.junit.Assert;
import org.junit.Test;
import org.sda.web.database.configuration.DatasourceConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

public class DatasourceConfigurationTest {

    @Test
    public void shouldReturnConnectionInstance() throws SQLException {
        // given
        DatasourceConfiguration underTest = new DatasourceConfiguration();

        // when
        Connection instance = underTest.getConnection();

        // then
        Assert.assertNotNull(instance);
    }
}
