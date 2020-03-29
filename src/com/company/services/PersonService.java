package com.company.services;

import com.company.domain.PersonRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;


//todo
// While deleting , if instance is absent in db -> Success message occurs. "Note not found" must be given instead.
// Main menu returned when incorrect input found while deleting  -> "Try again" !!!!!!!!!
// Add @Step Back@ function ко всему
// REMOVE "SUCCESS" where it is not needed

public class PersonService {

    private static PersonService instance;
    private List<String> book;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    private PersonService(List<String> book) {
        this.book = book;
    }

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
        book.add(currentRecord.toString());
    }

    public void delete() throws IOException {
        String searchingBy;
        while (true) {
            System.out.println("Use way in which you want to delete a record:\n" +
                    "1. By phone number\n" +
                    "2. By first name\n" +
                    "3. By second name\n" +
                    "4. By age \n" +
                    "5. Delete all\n" +
                    "6. Go back\n");
            searchingBy = reader.readLine();
            if (searchingBy.equals("1")) {
                System.out.println("Enter phone you want delete a record:");
                searchingBy = inputPhone();
                break;
            } else if (searchingBy.equals("2")) {
                System.out.println("Enter first name you want delete a record:");
                searchingBy = inputName();
                break;
            } else if (searchingBy.equals("3")) {
                System.out.println("Enter second name you want delete a record:");
                searchingBy = inputName();
                break;
            } else if (searchingBy.equals("4")) {
                System.out.println("Enter age which you want delete a record by: ");
                searchingBy = inputAge();
                break;
            } else if (searchingBy.equals("5")) {
                System.out.println("Are you sure? Input Yes/No");
                searchingBy = reader.readLine();
                if (searchingBy.equalsIgnoreCase("yes")) {
                    System.out.println("Success");
                    book = Collections.emptyList();
                }
                return;
            } else if (searchingBy.equals("6")) {
                return;
            } else
                System.out.println("Invalid character, choose number from 1 to 6: ");
        }

        String searchingByCopy = (searchingBy);
        book.removeIf(s -> s.contains(searchingByCopy));
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

    public static PersonService getInstance(List<String> book) {
        if (instance == null) {
            instance = new PersonService(book);
        }
        return instance;
    }

    public List<String> getBook() {
        return this.book;
    }

    public void printAll(){
        System.out.println(book);
    }
}
