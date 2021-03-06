package member;

import ui.Role;
import ui.Status;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Random;

//@author Fie og Etienne

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
    private Role role;


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

    public Role getRole(){
        return role;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String getIsActiveAsString(){
        String status;
        if (isActive){
            status = setIsActive();
        } else {
            status = setIsPassive();
        }
        return status;
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
        isActive = !isActive;
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
        try {
            firstTwo = name.substring(0, 2).toLowerCase();
            if (firstTwo.contains(" ")){
                firstTwo = name.substring(0,1).toLowerCase() + name.substring(0,1).toLowerCase();
            }
            lastTwo = name.substring(name.lastIndexOf(space) + 1, name.lastIndexOf(space) + 3).toLowerCase();
        } catch (IndexOutOfBoundsException e) {
            try {
                lastTwo = name.substring(name.lastIndexOf(space) + 1, name.lastIndexOf(space) + 2).toLowerCase() +
                        name.substring(name.lastIndexOf(space) + 1, name.lastIndexOf(space) + 2).toLowerCase();
                if (firstTwo.equals("")){
                    firstTwo = lastTwo;
                }
            } catch (IndexOutOfBoundsException f) {
                firstTwo = name.substring(0, 1).toLowerCase() + name.substring(0, 1).toLowerCase();
                lastTwo = name.substring(0, 1).toLowerCase() + name.substring(0, 1).toLowerCase();
            }
        }
        return firstTwo + lastTwo + numbersFormat;
    }
    // Bruges til at s??ge efter et specifikt medlem
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd???MM???yyyy");
        String formattedDateTime = birthDate.format(formatter);
        return formattedDateTime;
    }

    public String toString() {
        return name + " " + membershipNumber;
    }
}
