package org.sda.web.global;

import org.sda.web.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum UsersListSingletonEnum {
    INSTANCE;

    private Map<String, List<User>> users;

    public Map<String, List<User>> getUsers() {
        return users;
    }

    public List<User> getUsersForAdmin(String admin) throws IllegalAccessException {
        if (admin == null) {
            throw new IllegalAccessException();
        }
        return users.get("admin");
    }

    public void addUser(String admin, User user) {
        if (users == null) {
            users = new HashMap<>();
        }
        users.putIfAbsent(admin, new ArrayList<>());
        List<User> listForAdmin = this.users.get(admin);
        listForAdmin.add(user);
    }

}
