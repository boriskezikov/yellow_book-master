package com.company;

import com.company.services.PersonService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//todo delete implementation


public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static List<String> consoleValues = new ArrayList<>() {{
        add("1");
        add("2");
        add("3");
        add("4");
        add("5");
    }};



    public static void main(String[] args) throws Exception {
        startUI();
    }

    public static void startUI() throws Exception {
        PersonService service = new PersonService();

        while (true) {
            System.out.println("Choose :\n " +
                    "1.Add new record to the phone book\n " +
                    "2.Edit user record\n " +
                    "3.Delete a record\n " +
                    "4.Print all stored records\n " +
                    "5.Quit\n");

            String userInput = reader.readLine();
            if (consoleValues.contains(userInput)) {
                if (userInput.equals("1")) {
                    service.addRecord();
                }
                else if (userInput.equals("2")) {
                    System.out.println("Second func");
                }
                else if (userInput.equals("3")) {
                    service.delete();
                }
                else if (userInput.equals("4")) {
                    service.printAll();
                }
                else if (userInput.equals("5")) {
                    System.out.println("Are you sure? Input: Yes/No");
                    String userInput1 = reader.readLine();
                    if (userInput1.toLowerCase().equals("yes"))
                        System.exit(0);
                    else
                        startUI();

                }

            }
            else System.out.println("Invalid data");
        }
    }
}

