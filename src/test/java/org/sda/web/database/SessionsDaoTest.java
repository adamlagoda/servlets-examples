package org.sda.web.database;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.sda.web.database.dao.SessionsDao;

public class SessionsDaoTest {

    @After
    public void removeSession() {
        SessionsDao dao = new SessionsDao();
        dao.delete(1);
    }

    @Test
    public void shouldInsertSession() {
        // given
        SessionsDao dao = new SessionsDao();

        // when
        dao.save("admin",1, 1);

        // then
        Assert.assertTrue(dao.hasSession(1));
    }
}
