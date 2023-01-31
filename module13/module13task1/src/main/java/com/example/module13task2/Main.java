package com.example.module13task2;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Utils utils = new Utils();

        int maxPostId = utils.readPosts().get();
        System.out.println("The biggest post ID is: " + maxPostId + "\n");

        List<Comment> comments = utils.readComments();
        utils.printCommentsBody(comments);

        utils.writeCommentsToFile(comments, maxPostId);
    }
}
