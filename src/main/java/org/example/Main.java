package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Enter a command");
            return;
        }

        if (args.length == 1) {
            System.out.println("Invalid input");
            return;
        }

        TaskOperations taskOperations = null;
        try {
            taskOperations = new TaskOperations();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (args[0]) {
            case "add":


                try {
                    int id = taskOperations.addTask(args[1]);
                    System.out.printf("Task added successfully (ID: %d)%n", id);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Not registered command");
        }
    }
}