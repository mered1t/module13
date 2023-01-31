package com.example.module13task3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.List;

public class Utils {

    public List<Task> readTasks() throws IOException, InterruptedException {

        final String url = "https://jsonplaceholder.typicode.com/users/1/todos";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.body(), new TypeReference<> () {});
    }

    public void printNotCompleted(List<Task> tasks){

        List<Task> notCompletedTasks = tasks.stream()
                .filter(task -> !task.isCompleted())
                .toList();

        notCompletedTasks.forEach(task -> System.out.println("TASK WITH ID: "
                + task.getId()
                + " -> "
                + task.getTitle()));
    }
}
