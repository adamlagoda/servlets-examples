package org.sda.web.database;

import org.junit.Assert;
import org.junit.Test;
import org.sda.web.database.dao.AdminsDao;

public class AdminsDaoTest {

    @Test
    public void shouldFindAdmin() {
        // given
        AdminsDao underTest = new AdminsDao();

        // when
        boolean found = underTest.hasAdmin("admin", "admin123");

        // then
        Assert.assertTrue(found);
    }
}
