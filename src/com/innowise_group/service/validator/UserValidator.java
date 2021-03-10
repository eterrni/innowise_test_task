package com.innowise_group.service.validator;

import com.innowise_group.entity.Role;

import java.util.ArrayList;
import java.util.List;

import static com.innowise_group.entity.Role.SUPER_ADMIN;

public final class UserValidator {

    private static final String EMAIL_REGEXP = "^(?=.{3,30}$)[^\\s]+@[^\\s]+\\.[^\\s]+$";
    private static final String PHONE_REGEXP = "^[0-9\\s-+()]{7,30}$";

    public static boolean validateName(String name) {
        return name != null && name.length() > 2 && name.length() < 15;
    }

    public static boolean validateSurname(String surname) {
        return surname != null && surname.length() > 3 && surname.length() < 15;
    }

    public static boolean validateEmail(String email) {
        if (email != null && email.length() != 0) {
            return email.matches(EMAIL_REGEXP);
        }
        return false;
    }

    public static boolean validatePhones(String phones) {
        if (phones == null) {
            return false;
        }

        String phonesWithoutSpace = phones.replaceAll(" ", "");
        String[] phonesArray = phonesWithoutSpace.split(",");

        if (phonesArray.length > 3 || phonesArray.length < 1) {
            System.out.println("Введите пожалуйста от 1 до 3 мобильных телефонов через запятую");
            return false;
        }

        for (String phone : phonesArray) {
            if (!phone.matches(PHONE_REGEXP)) {
                System.out.println("Формат мобильного телефона: 375XX XXXXXXX");
                return false;
            }
        }

        return true;
    }

    public static boolean validateRoles(String roles) {

        if (roles == null) {
            return false;
        }

        String rolesWithoutSpace = roles.replaceAll(" ", "");
        String[] rolesArray = rolesWithoutSpace.split(",");

        if (rolesArray.length > 2) {
            System.out.println("Пользователь может иметь максимум 2 роли");
        }

        List<Role> rolesEnum = new ArrayList<>();
        for (String role : rolesArray) {
            try {
                Role roleEnum = Role.valueOf(role.toUpperCase());
                rolesEnum.add(roleEnum);
            } catch (IllegalArgumentException ex) {
                System.out.println("Существующие роли: USER (ур1), CUSTOMER(ур1), ADMIN (ур2), PROVIDER(ур2), SUPER_ADMIN (ур3)");
                return false;
            }
        }

        int roleLevel = 0;
        for (Role role : rolesEnum) {
            if (role.equals(SUPER_ADMIN) && rolesEnum.size() > 1) {
                System.out.println("Если у пользователя указана роль SUPER_ADMIN - другие роли выбирать запрещено");
                return false;
            }
            if (role.getRoleLevel() == roleLevel) {
                System.out.println("Пользователь не может иметь роли одного уровня");
                return false;
            }
            roleLevel = role.getRoleLevel();
        }
        return true;
    }
}
