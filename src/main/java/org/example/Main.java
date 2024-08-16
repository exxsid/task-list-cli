package org.example;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Enter a command");
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
            case "mark-done":
                try {
                    int id = Integer.parseInt(args[1]);
                    taskOperations.markDone(id);
                    System.out.println("Task status successfully updated to 'done'");
                } catch (NumberFormatException | IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "list":
                // print all the tasks
                if (args.length == 1) {
                    List<Task> tasks = taskOperations.getTaskList();
                    printTasks(tasks);
                    break;
                }

                // print the to do tasks
                if(args[1].equals("todo")) {
                    List<Task> todoTasks = taskOperations.getTaskList().stream()
                            .filter(task -> task.getStatus().equals("todo"))
                            .toList();
                    printTasks(todoTasks);
                    break;
                }

                //print all in-progress tasks
                if (args[1].equals("in-progress")) {
                    List<Task> inProgressTasks = taskOperations.getTaskList().stream()
                            .filter(task -> task.getStatus().equals("in-progress"))
                            .toList();
                    printTasks(inProgressTasks);
                    break;
                }

                break;
            default:
                System.out.println("Not registered command");
        }
    }

    private static void printTasks(List<Task> tasks) {
        // when the list is empty print "No Task"
        if (tasks.isEmpty()) {
            System.out.println("No Task");
            return;
        }

        String tableSpacingFormat = "%-5s %-30s %-10s %-30s %-30s\n";
        System.out.printf(
                tableSpacingFormat,
                "ID",
                "Description",
                "Status",
                "Created At",
                "Updated At"
        );

        for (Task task : tasks) {
            System.out.printf(
                    tableSpacingFormat,
                    task.getId(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getCreatedAt(),
                    task.getUpdatedAt()
            );
        }
    }
}