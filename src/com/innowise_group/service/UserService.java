package com.innowise_group.service;

import com.innowise_group.entity.User;
import com.innowise_group.repository.UserRepository;
import com.innowise_group.service.util.UserBuilder;
import com.innowise_group.service.validator.UserValidator;

import java.util.Scanner;

public final class UserService {

    private static final Scanner scan = new Scanner(System.in);
    private static final String MAIN_MENU = "1. Создать пользователя\n"
            + "2. Просмотр существующих пользователей\n"
            + "3. Удалить пользователя\n"
            + "4. Редактировать информацию пользователя\n"
            + "5. Выход";
    private static final String MENU_EMAIL_REMOTE_USER = "Введите email пользователя, которого хотите удалить";
    private static final String MENU_EMAIL_UPDATE_USER = "Введите email пользователя, которого хотите обновить";

    public static void mainMenu() {
        System.out.println(MAIN_MENU);
    }

    public static void createUserMenu() {
        String name = null;
        while (!UserValidator.validateName(name)) {
            System.out.print("Введите имя(от 3 до 15 символов): ");
            name = scan.next();
        }

        String surname = null;
        while (!UserValidator.validateSurname(surname)) {
            System.out.print("Введите фамилию(от 3 до 15 символов): ");
            surname = scan.next();
        }

        String email = null;
        while (!UserValidator.validateEmail(email)) {
            System.out.print("Введите email: ");
            email = scan.next();
        }

        String roles = null;
        while (!UserValidator.validateRoles(roles)) {
            System.out.print("Введите роль/и: ");
            roles = new Scanner(System.in).nextLine();
        }

        String phones = null;
        while (!UserValidator.validatePhones(phones)) {
            System.out.print("Введите мобильный номер телефона: ");
            phones = new Scanner(System.in).nextLine(); // made so that it was possible to enter multiple mobile phones separated by a comma with a space example: 37533 3363333, 37529 322 32 32
        }

        User user = UserBuilder.build(name, surname, email, roles, phones);
        UserRepository.save(user);
    }

    public static void viewExistingUsersMenu() {
        System.out.println(UserRepository.readAll());
    }

    public static void deleteUserMenu() {
        System.out.println(UserRepository.readAll());
        System.out.println(MENU_EMAIL_REMOTE_USER);
        UserRepository.delete(scan.next());
    }

    public static void updateUserMenu() {
        System.out.println(UserRepository.readAll());
        System.out.println(MENU_EMAIL_UPDATE_USER);
        String email = scan.next();
        User read = UserRepository.read(email);
        if (read != null) {
            System.out.println(read);
            String name = null;
            while (!UserValidator.validateName(name)) {
                System.out.print("Введите имя(от 3 до 15 символов): ");
                name = scan.next();
            }

            String surname = null;
            while (!UserValidator.validateSurname(surname)) {
                System.out.print("Введите фамилию(от 3 до 15 символов): ");
                surname = scan.next();
            }

            String roles = null;
            while (!UserValidator.validateRoles(roles)) {
                System.out.print("Введите роль/и: ");
                roles = new Scanner(System.in).nextLine();
            }

            String phones = null;
            while (!UserValidator.validatePhones(phones)) {
                System.out.print("Введите мобильный номер телефона: ");
                phones = new Scanner(System.in).nextLine();
            }

            User user = UserBuilder.build(name, surname, email, roles, phones);
            UserRepository.update(user);
        }
    }

    public static int resultOfMainMenu() {
        int number;
        do {
            System.out.println("Выберите пункт меню!");
            while (!scan.hasNextInt()) {
                System.out.println("Введёное значение не является числом!");
                scan.next();
            }
            number = scan.nextInt();
        } while (number <= 0 || number > 5);
        return number;
    }

}
