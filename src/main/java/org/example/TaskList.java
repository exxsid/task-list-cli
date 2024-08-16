package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskList {

    private static final String JSON_FILE_SOURCE = "src/main/resources/tasks.json";


    public static Map<Integer, Task> getTasks() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Task> json = mapper.readValue(new File(JSON_FILE_SOURCE),
                mapper.getTypeFactory().constructCollectionType(List.class, Task.class));

        return json.stream()
                .collect(Collectors.toMap(Task::getId, task -> task));

    }
}
