package com.company.services;

public class Validator {
    private static boolean flag = true;

    public static boolean validateName(String name) {

        return name.matches("\\w+.(|-|)\\w+");
    }

    public static boolean validateNumber(String number) {
        if (number.length() != 11)
            return false;
        return number.matches("\\d+");
    }

    public static boolean validateAge(String age) {
        if (!age.matches("\\d+"))
            return false;
        int ageInt = Integer.parseInt(age);
        return  ageInt > 0 && ageInt <140;


    }
}