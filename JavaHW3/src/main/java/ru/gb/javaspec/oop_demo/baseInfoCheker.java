package ru.gb.javaspec.oop_demo;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface baseInfoCheker {
//    default  Worker checkData(int yearOfBirthday, String firstName, String secondName, int innId){
//
//        // Проверка возраста (только год)
//        int curentYear = LocalDate.now().getYear();
//        if (curentYear-yearOfBirthday < MIN_AGE || curentYear > MAX_AGE) {
//            throw new IllegalArgumentException("Некорректный возраст");
//        }
//
//        // Проверка ИНН
//        if (String.valueOf(innId).length()!=12){
//            throw new IllegalArgumentException("Некорректный ИНН");
//        }
//
//        //Проверка Имени
//        if (firstName == null) {
//            throw new IllegalArgumentException("Имя должно быть определено");
//        } else {
//            Pattern namePatern = Pattern.compile("^[a-zA-Zа-яА-Я]*$");
//            Matcher nameMAtcher = namePatern.matcher(firstName);
//            if (!nameMAtcher.find() || firstName.length() < 2 || firstName.length() > 20) {
//                throw new IllegalArgumentException("Некорректное имя");
//            }
//        }
//
//        if (secondName!=null) {
//
//            Pattern namePatern = Pattern.compile("^[a-zA-Zа-яА-Я]*$");
//            Matcher nameMAtcher = namePatern.matcher(secondName);
//            if (!nameMAtcher.find() || secondName.length() < 2 || secondName.length() > 20) {
//                secondName=null;
//            }
//        }
//
//        return null;
//    }
}
