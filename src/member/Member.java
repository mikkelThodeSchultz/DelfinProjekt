package member;

import  java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class Member {
    private String name;
    private String phoneNumber;
    private String email;
    private String homeAddress;
    private LocalDate birthDate;
    private String membershipNumber;
    private boolean isActive;
    private boolean hasPaid;

    public Member(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
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

    public void createNewMember() {

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

