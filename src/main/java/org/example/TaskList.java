package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TaskList {

    private static final String JSON_FILE_SOURCE = "src/main/resources/tasks.json";

    public static List<Task> getTasks() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(JSON_FILE_SOURCE),
                mapper.getTypeFactory().constructCollectionType(List.class, Task.class));
    }
}
