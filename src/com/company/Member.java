package com.company;

import java.time.LocalDate;
import java.time.Period;

public class Member {
    private String name;
    private String phoneNumber;
    private String email;
    private String homeAddress;
    private LocalDate birthDate;
    private String membershipNumber;
    private boolean hasSeniorDiscount;
    private boolean isSeniorSwimmer;

    public Member(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year, String membershipNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeAddress = homeAddress;
        this.birthDate = LocalDate.of(year, month, day);
        this.membershipNumber = membershipNumber;//TODO autogenerer - tjek eksamensøv.9!
    }

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        } else return -1;
    }
    //TODO skal være i formand klassen.
    public void createNewMember() {
        Member member = new Member("Torben", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000, "hjg5");
        int discountAge = 60;
        int seniorAge = 18;

        if (member.calculateAge() >= discountAge) {
            hasSeniorDiscount = true;
        }
        if (member.calculateAge() >= 18) {
            isSeniorSwimmer = true;
        }
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

    public LocalDate getBirthDate() {
        return birthDate;
    }
}

