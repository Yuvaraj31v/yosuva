package com.ideas2it.util;

import java.time.LocalDate;
import java.time.Period;


public class Validation {

    public static boolean validatePhoneNumber(String phoneNumber) {
        String pattern = "^\\d{10}$";
        return phoneNumber.matches(pattern);
    }

    public static int getExperience(LocalDate employeeDateOfJoin) {
        LocalDate currentDate = LocalDate.now();
        int employeeExperience = Period.between(employeeDateOfJoin, currentDate).getDays();
        System.out.println(employeeExperience);
        return employeeExperience;
    }

    public static int getAge(LocalDate employeeDateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        int employeeAge = Period.between(employeeDateOfBirth, currentDate).getYears();
        return employeeAge;
    }

    public static String validateDate(String date) {
        String[] dates = date.split("\\W");
        String dateToValidate = null;
        if (dates.length == 3) {
            if (dates[0].length() == 2) {
                dateToValidate = dates[2] + "-" + dates[1] + "-" + dates[0];
            } else {
                dateToValidate = dates[0] + "-" + dates[1] + "-" + dates[2];
            }
        } else {
            dateToValidate = date;
        }
        return dateToValidate;
    }
}
