package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Enter a command");
            return;
        }

        if (args.length <= 1) {
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
            case "update":
                try {
                    int id = Integer.parseInt(args[1]);
                    String description = args[2];
                    taskOperations.updateTask(id, description);
                    System.out.println("Task updated successfully");
                } catch (NumberFormatException e) {
                    System.out.println("Enter a proper id number.");
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                }
                break;
            case "delete":
                try {
                    int id = Integer.parseInt(args[1]);
                    taskOperations.deleteTask(id);
                    System.out.println("Task deleted successfully");
                } catch (NumberFormatException | IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "mark-in-progress":
                try {
                    int id = Integer.parseInt(args[1]);
                    taskOperations.markInProgress(id);
                    System.out.println("Task status successfully updated to 'in progress'");
                } catch (NumberFormatException | IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Not registered command");
        }
    }
}