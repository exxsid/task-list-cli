package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskOperations {

    private static final String JSON_FILE_SOURCE = "src/main/resources/tasks.json";

    private List<Task> tasks = TaskList.getTasks();

    public TaskOperations() throws IOException {
    }

    public int addTask(String description) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);
        int newId = getNewId();
        Task task = new Task(newId, description, "todo", now, now);
        tasks.add(
                task
        );

        saveTasks();

        return newId;
    }

    private static List<Task> getTasks() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(JSON_FILE_SOURCE,
                mapper.getTypeFactory().constructCollectionType(List.class, Task.class));
    }

    private int getNewId() {
        return tasks.get(tasks.size() - 1).getId() + 1;
    }

    private void saveTasks() throws IOException {
        String JSON_FILE_SOURCE = "src/main/resources/tasks.json";

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        OutputStream outputStream = new FileOutputStream(JSON_FILE_SOURCE);
        mapper.writeValue(out, tasks);
        out.writeTo(outputStream);
        outputStream.close();
    }

}
