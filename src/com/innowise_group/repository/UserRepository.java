package com.innowise_group.repository;

import com.innowise_group.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class UserRepository {

    public static void save(User user) {
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("users.txt", true))) {
            objectOutputStream.writeObject(user);
        } catch (IOException ex) {
            System.out.println("Ошибка сохранения пользователя");
        }
    }

    public static List<User> readAll() {
        List<User> users = new ArrayList<>();
        ObjectInputStream ois = null;
        User user;
        try (FileInputStream fis = new FileInputStream("users.txt")) {
            while (true) {
                try {
                    ois = new ObjectInputStream(fis);
                    user = (User) ois.readObject();
                    users.add(user);
                } catch (EOFException ignored) {
                    if (Objects.nonNull(ois)) {
                        ois.close();
                    }
                    break;
                }
            }
            return users;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка в чтении пользователей");
            return users;
        }
    }

    public static User read(String email) {
        ObjectInputStream ois = null;
        User user;
        try (FileInputStream fis = new FileInputStream("users.txt")) {
            while (true) {
                try {
                    ois = new ObjectInputStream(fis);
                    user = (User) ois.readObject();
                    if (user.getEmail().equals(email)) {
                        return user;
                    }
                } catch (EOFException ignored) {
                    if (Objects.nonNull(ois)) {
                        ois.close();
                    }
                    System.out.println("Пользователь с таким email не был найден");
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка в чтении пользователей");
        }
        return null;
    }

    public static void delete(String email) {
        List<User> users = readAll();
        ObjectOutputStream objectOutputStream = null;
        try (final FileOutputStream fileOutputStream = new FileOutputStream("users.txt", false)) {
            for (User user : users) {
                if (!user.getEmail().equals(email)) {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(user);
                }
            }
        } catch (IOException ex) {
            System.out.println("Ошибка удаления пользователя");
        } finally {
            if (Objects.nonNull(objectOutputStream)) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    System.out.println("Ошибка при удалении");
                }
            }
        }
    }

    public static void update(User user) {
        delete(user.getEmail());
        save(user);
    }
}
//
//    public static boolean update() {
//    }


