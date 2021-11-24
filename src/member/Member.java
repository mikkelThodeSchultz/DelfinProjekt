package member;

import  java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class Member {
    private String name;
    private String phoneNumber;
    private String email;
    private String homeAddress; //Adresse skal muligvis oprettes som sit eget objekt - burde gøre validering af denne
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
        this.membershipNumber = generateMembershipNumber();
    }


    private String generateMembershipNumber (){
        String space = " ";
        String firstTwo = "";
        String lastTwo = "";
        Random random = new Random();
        int numbers = random.nextInt(10000);
        String numbersFormat = String.format("%04d",numbers);
        firstTwo = name.substring(0,2).toLowerCase();
        lastTwo = name.substring(name.lastIndexOf(space)+1,name.lastIndexOf(space)+3).toLowerCase();
        return firstTwo+lastTwo+numbersFormat;
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

