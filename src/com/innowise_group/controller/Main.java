package com.innowise_group.controller;

import com.innowise_group.service.UserService;

public class Main {
    public static void main(String[] args) {

        UserService.mainMenu();
        while (true) {
            switch (UserService.resultOfMainMenu()) {
                case 1:
                    UserService.createUserMenu();
                    UserService.mainMenu();
                    break;
                case 2:
                    UserService.viewExistingUsersMenu();
                    UserService.mainMenu();
                    break;
                case 3:
                    UserService.deleteUserMenu();
                    UserService.mainMenu();
                    break;
                case 4:
                    UserService.updateUserMenu();
                    UserService.mainMenu();
                    break;
                case 5:
                    System.exit(0);
            }
        }

    }
}
