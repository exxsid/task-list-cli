package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class TaskOperations {


    private Map<Integer, Task> tasks = TaskList.getTasks();

    public TaskOperations() throws IOException {
    }

    public int addTask(String description) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);
        int newId = getNewId();
        Task task = new Task(newId, description, "todo", now, now);
        tasks.put(newId, task);

        saveTasks();

        return newId;
    }


    public void updateTask (int id, String description) throws IOException {
        String dateTimeNow = getDateTimeNow();

        tasks.get(id).setDescription(description);
        tasks.get(id).setUpdatedAt(dateTimeNow);

        saveTasks();
    }

    public void deleteTask (int id) throws IOException {
        tasks.remove(id);
        saveTasks();
    }

    public void markInProgress(int id) throws IOException {
        tasks.get(id).setStatus("in progress");
        saveTasks();
    }

    public void markDone(int id) throws IOException {
        tasks.get(id).setStatus("done");
        saveTasks();
    }


    private int getNewId() {
        return tasks.values().stream().toList().get(tasks.size() - 1).getId() + 1;
    }

    private void saveTasks() throws IOException {
        String JSON_FILE_SOURCE = "src/main/resources/tasks.json";

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // convert map task list to list
        List<Task> taskList = tasks.values().stream()
                .toList();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStream outputStream = new FileOutputStream(JSON_FILE_SOURCE);
        mapper.writeValue(out, taskList);
        out.writeTo(outputStream);
        outputStream.close();
    }

    private String getDateTimeNow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

}
