package org.sda.web.database.dao;

import org.sda.web.database.configuration.DatasourceConfiguration;

public class SessionsDao {

    private DatasourceConfiguration datasourceConfiguration;

    public SessionsDao() {
        datasourceConfiguration = new DatasourceConfiguration();
    }
}
