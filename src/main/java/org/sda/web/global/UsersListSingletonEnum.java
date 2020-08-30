package org.sda.web.global;

import org.sda.web.model.User;

import java.util.ArrayList;
import java.util.List;

public enum UsersListSingletonEnum {
    INSTANCE;

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }

}
