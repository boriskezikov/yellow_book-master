package com.company.services;

import com.company.domain.PersonRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


//todo
// While deleting , if instance is absent in db -> Success message occurs. "Note not found" must be given instead.
// Main menu returned when incorrect input found while deleting  -> "Try again" !!!!!!!!!
// Add @Step Back@ function ко всему
// REMOVE "SUCCESS" where it is not needed

public class PersonService {

    private static String filePath = "C:\\Users\\boke0619\\Downloads\\yellow_book-master\\src\\com\\company\\database.txt";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public void addRecord() throws IOException {
        PersonRecord currentRecord = PersonRecord.recordProvider();
        System.out.println("Input First name: ");
        currentRecord.setFirstName(inputName());
        System.out.println("Input Second name: ");
        currentRecord.setSecondName(inputName());
        System.out.println("Input phone number: ");
        currentRecord.setPhoneNumber(inputPhone());
        System.out.println("Input age: ");
        currentRecord.setAge(inputAge());
        writeRecord(currentRecord.toString());
    }

    public void delete() throws Exception {
        Scanner scanner = new Scanner(Paths.get(filePath), StandardCharsets.UTF_8.name());
        String searching;
        while (true) {
            System.out.println("Use way in which you want to delete a record:\n" +
                    "1. By phone number\n" +
                    "2. By first name\n" +
                    "3. By second name\n" +
                    "4. By age \n" +
                    "5. Delete all\n" +
                    "6. Go back\n");
            searching = reader.readLine();
            if (searching.equals("1")) {
                System.out.println("Enter phone you want delete a record:");
                searching = inputPhone();
                break;
            } else if (searching.equals("2")) {
                System.out.println("Enter first name you want delete a record:");
                searching = inputName();
                break;
            } else if (searching.equals("3")) {
                System.out.println("Enter second name you want delete a record:");
                searching = inputName();
                break;
            } else if (searching.equals("4")) {
                System.out.println("Enter age which you want delete a record by: ");
                searching = inputAge();
                break;
            } else if (searching.equals("5")) {
                System.out.println("Are you sure? Input Yes/No");
                searching = reader.readLine();
                if (searching.equalsIgnoreCase("yes")) {
                    System.out.println("Success");
                }
                return;
            } else if (searching.equals("6")) {
                return;
            } else
                System.out.println("Invalid character, choose number from 1 to 6: ");
        }

        List<String> notes = new ArrayList<>();

        while (scanner.hasNextLine()) {
            notes.add(scanner.nextLine());
        }
        String finalSearching = searching;
        notes.removeIf(s -> s.contains(finalSearching));
//        ______________________________
        File fileTOWrite = new File(filePath);
        FileWriter writer = new FileWriter(fileTOWrite, true);
        notes.forEach(note->{
            try {
                writer.write(note);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }

    private String inputName() throws IOException {
        while (true) {
            String name = reader.readLine();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            if (Validator.validateName(name)) {
                System.out.println("Success");
                return name;
            } else
                System.out.println("Incorrect input. Name can consist of only Latin chars. Please try again!");
        }
    }

    private String inputPhone() throws IOException {
        while (true) {
            String number = reader.readLine().replace("+7", "8");
            if (Validator.validateNumber(number)) {
                System.out.println("Success");
                return number;
            } else
                System.out.println("Incorrect input. Number can consist of only numbers." +
                        " Should be 11 numbers.Please try again!");
        }
    }

    private String inputAge() throws IOException {
        while (true) {
            String age = reader.readLine();
            if (Validator.validateAge(age)) {
                System.out.println("Success");
                return age;
            } else
                System.out.println("Incorrect input. Age can consist of only numbers." +
                        " Please try again!");
        }
    }

    private void writeRecord(String stringToWrite) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, StandardCharsets.UTF_8, true))) {
            writer.println(stringToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAll() {
        try (Scanner scanner = new Scanner(Paths.get(filePath), StandardCharsets.UTF_8.name())) {
            System.out.println(scanner.useDelimiter("\\A").next());
        } catch (NoSuchElementException | IOException e) {
            System.out.println("Yellow book is empty!");
        }
    }
}
