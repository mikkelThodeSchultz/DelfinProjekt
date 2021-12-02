//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import ui.Role;
import ui.Status;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public abstract class Member {
    private String name;
    private int age;
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
        this.age = calculateAge();
        this.isActive = true;
        this.hasPaid = false;
    }

    public Member(int i, Member member, Status statusMedlemskab, Status statusDiscipline) {
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String setIsActive(){
        return "aktiv";
    }

    public String setIsPassive(){
        return "passiv";
    }

    public boolean getHasPaid() {
        return hasPaid;
    }

    public int getAge() {
        return age;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public void setActive() {
        if (!isActive)
            isActive = true;
        else {
            isActive = false;
        }
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return birthDate != null ? Period.between(birthDate, currentDate).getYears() : -1;
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

    public boolean matches(String userInputSearch){
        boolean match;
        if (membershipNumber.contains(userInputSearch)){
            match = true;
        } else if (name.toLowerCase().contains(userInputSearch.toLowerCase())){
            match = true;
        } else {
            match = false;
        }
        return match;
    }

    public String oldDateToString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd–MM–yyyy");
        String formattedDateTime = birthDate.format(formatter);
        return formattedDateTime;
    }

    public String toString() {
        return name + " " + membershipNumber;
    }


}
