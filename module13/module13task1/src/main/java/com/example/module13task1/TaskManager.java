package com.example.module13task1;

import java.util.Scanner;

public class TaskManager{

    WebPageChanger webPageChanger = new WebPageChanger();

    public void chooseTask() throws Exception {

        StringBuilder answer = new StringBuilder();

        info();

        while(!answer.toString().equalsIgnoreCase("no")){
        switch (askForTaskNumber()){
            case 1:
                webPageChanger.createJson();
                break;
            case 2:
                webPageChanger.updateJson();
                break;
            case 3:
                webPageChanger.deleteJson();
                break;
            case 4:
                webPageChanger.getAllJson();
                break;
            case 5:
                webPageChanger.getJsonById();
                break;
            case 6:
                webPageChanger.getJsonByUsername();
                break;
            }
            answer = askForContinue();
        }
    }

    private int askForTaskNumber(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the number from 1 to 6: ");
        int number = scanner.nextInt();
        System.out.println();

        return number;
    }

    private StringBuilder askForContinue(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to continue? Enter YES or NO: ");
        StringBuilder ans = new StringBuilder(scanner.nextLine());
        while(!ans.toString().equalsIgnoreCase("yes") && !ans.toString().equalsIgnoreCase("no")){
            System.out.print("Enter correct answer: ");
            ans = new StringBuilder(scanner.nextLine());
        }

        return ans;
    }

    private void info(){

        System.out.println("1 - створення нового об'єкту\n" +
                "2 - оновлення об'єкту\n" +
                "3 - видалення об'єкту\n" +
                "4 - отримання інформації про всіх користувачів\n" +
                "5 - отримання інформації про користувача за id\n" +
                "6 - отримання інформації про користувача за username\n");
    }
}
