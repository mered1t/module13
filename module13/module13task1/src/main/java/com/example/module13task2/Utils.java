package com.example.module13task2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;

public class Utils {
    public Optional<Integer> readPosts() throws IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users/1/posts";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> commentResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Post> posts = objectMapper.readValue(commentResponse.body(), new TypeReference<>() {});
        return posts.stream()
                .map(Post::getId)
                .max(Integer::compareTo);
    }

    public List<Comment> readComments() throws IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/posts/10/comments";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest commentRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> commentResponse = client.send(commentRequest, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(commentResponse.body(), new TypeReference<>() {});
    }

    public void printCommentsBody(List<Comment> comments){

        for (Comment comment : comments) {
            System.out.println("COMMENT WITH ID " + comment.getId() +
                    ": " + comment.getBody() + "\n");
        }
    }

    public void writeCommentsToFile(List<Comment> comments, int maxPostId){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("user-" + maxPostId + "-post-" + comments.size() + "-comments.json");

        try (FileWriter fileWriter = new FileWriter(file)) {
                String jsonString = gson.toJson(comments);
                fileWriter.write(jsonString);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
