//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import java.time.LocalDate;
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


    //NEEDED FOR JSON! DO NOT DELETE
    public Member(){}

    public Member(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeAddress = homeAddress;
        this.birthDate = LocalDate.of(year, month, day);
        this.membershipNumber = generateMembershipNumber();
    }

    private String generateMembershipNumber() {
        String space = " ";
        String firstTwo = "";
        String lastTwo = "";
        Random random = new Random();
        int numbers = random.nextInt(10000);
        String numbersFormat = String.format("%04d", numbers);
        firstTwo = name.substring(0, 2).toLowerCase();
        lastTwo = name.substring(name.lastIndexOf(space) + 1, name.lastIndexOf(space) + 3).toLowerCase();
        return firstTwo + lastTwo + numbersFormat;
    }

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return birthDate != null ? Period.between(birthDate, currentDate).getYears() : -1;
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

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public String toString() {
        return name + " " + membershipNumber;
    }
}
