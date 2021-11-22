package com.company;

import java.time.LocalDate;
import java.time.Period;

public class Member {
    private String name;
    private String phoneNumber;
    private String email;
    private String homeAddress;
    private LocalDate birthdate;

    public Member(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeAddress = homeAddress;
        this.birthdate = LocalDate.of(year,month,day);

    }

    public int calculateAge(){
        LocalDate currentDate = LocalDate.now();
        if (birthdate!=null) {
            return Period.between(birthdate, currentDate).getYears();
        }
        else return -1;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}

