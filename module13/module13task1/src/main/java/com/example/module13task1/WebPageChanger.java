package com.example.module13task1;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Scanner;

public class WebPageChanger {
    private HttpClient client = HttpClient.newHttpClient();
    private final static String url = "https://jsonplaceholder.typicode.com/users";

    protected void updateJson() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(url + "/2"))
                .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get("src/main/resources/sample.json")))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    protected void deleteJson() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/1"))
                .DELETE()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    protected void getAllJson() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    protected void createJson() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("src/main/resources/sample.json")))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    protected void getJsonById() throws Exception{

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(url + "/" + askForId()))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    protected void getJsonByUsername() throws Exception{

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(url + "?username=" + askForUsername()))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private String askForUsername(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username: ");
        String username = scanner.nextLine();

        return username;
    }

    private int askForId(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the id: ");
        int id = scanner.nextInt();

        return id;
    }
}
