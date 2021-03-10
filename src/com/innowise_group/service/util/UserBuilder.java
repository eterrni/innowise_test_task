package com.innowise_group.service.util;

import com.innowise_group.entity.Role;
import com.innowise_group.entity.User;

import java.util.ArrayList;
import java.util.List;

public final class UserBuilder {

    public static User build(String name, String surname, String email, String roles, String phones) {

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);

        String[] rolesArray = roles.replaceAll(" ", "").split(",");
        List<Role> roleList = new ArrayList<>();
        for (String role : rolesArray) {
            roleList.add(Role.valueOf(role.toUpperCase()));
        }
        user.setRoles(roleList);

        String[] phoneArray = phones.replaceAll(" ", "").split(",");
        List<String> phoneList = new ArrayList<>();
        for (String phone : phoneArray) {
            phoneList.add(phone);
        }
        user.setPhones(phoneList);

        return user;
    }
}
