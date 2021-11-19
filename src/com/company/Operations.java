package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Operations {
    Scanner scanner = new Scanner(System.in);

    public void readUserIpnut() throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("users_reg.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        FileInputStream fileInputStream = new FileInputStream("users_reg.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        System.out.println("Welcome to the app!");
        List<User> list = new ArrayList<>();

        while (true) {
            System.out.println("Please enter 'r' for registration or 'l' for login >");
            String input = scanner.nextLine();
            switch (input) {
                case "r":

                    System.out.println("If you want to add users type 'yes', if not type 'no' and see registered users >");
                    String response = scanner.nextLine();
                    if (response.equals("yes")) {
                        System.out.println("Registration started");
                        System.out.println("Enter username >");
                        String username = scanner.nextLine();
                        System.out.println("Enter password >");
                        String pass = scanner.nextLine();
                        User user = new User(username, pass);
                        if (!list.equals(user)) {
                            list.add(user);
                        } else {
                            System.out.println("User already registered");
                        }
                        System.out.println("User registered successfully!");
                        System.out.println(username + " logged in");
                        String nxline = scanner.nextLine();
                        if (nxline.equals("logout")) {
                            System.out.println(username + " Log out successful");
                            break;
                        }
                        objectOutputStream.writeObject(list);
                        objectOutputStream.flush();
                    } else if (response.equals("no")) {
                        System.out.println("type list and see registered users");
                        String printusers = scanner.nextLine();
                        if (printusers.equals("list")) {
                            deserializeObjs();
                        } else {
                            System.out.println("Wrong command");
                            break;
                        }
                    } else if (response.equals("l")) {
                        login();
                    } else {
                        System.out.println("Wrong command");
                        break;
                    }

                    break;

                case "l":
                    login();
                    break;

                default:
                    System.out.println("Wrong input");
                    break;
            }


        }


    }

    public void login() {
        List<User> s = deserializeObjs();
        while (true) {
            System.out.println("Enter username:");
            String uname = scanner.nextLine();
            System.out.println("Enter password:");
            String upass = scanner.nextLine();
            for (User value : s) {
                if (uname.equals(value.username) && upass.equals(value.password)) {
                    System.out.println(uname + " logged in");
                    break;
                }
            }
            for (User user : s) {
                if (!uname.equals(user.username) || !upass.equals(user.password)) {
                    System.out.println("Incorrect login infos!");
                    break;
                }
            }
            break;
        }
        String out = scanner.nextLine();
        if (out.equals("logout")) {
            System.out.println("Log out successfully");
        }
    }

    public static List<User> deserializeObjs() {
        List<User> objs = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("users_reg.txt"));
            objs = (List<User>) in.readObject();
            in.close();
        } catch (Exception e) {
        }
        System.out.println(objs);
        return objs;
    }


}

