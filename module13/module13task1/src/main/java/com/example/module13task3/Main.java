package com.example.module13task3;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Utils utils = new Utils();

        List<Task> tasks = utils.readTasks();
        utils.printNotCompleted(tasks);
    }
}
