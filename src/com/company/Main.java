package com.company;

public class Main {

    public static void main(String[] args) {

        Member member = new Member("Torben", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24,12,2000, "hjtr5");
        System.out.println(member.calculateAge());
        System.out.println(member.getBirthDate());

    }

}
