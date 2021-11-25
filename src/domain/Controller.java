//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain;

import file.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import member.Member;
import member.MemberList;
import member.StandardMember;
import ui.Status;
import ui.UserInterface;

public class Controller {
    private UserInterface ui = new UserInterface();
    private MemberList memberList = new MemberList();
    private Calculation calculation = new Calculation();
    private FileHandler FileHandler = new FileHandler();
    private ArrayList<Member> storedMembers;


    public Controller() {
    }

    public void start() {
        ui.getWelcomeMessage();
        boolean goAgain = true;
        while(goAgain){
           String choice = ui.getMainMenu();
           switch(choice){
               case "1" -> memberMenu();
               case "2" -> paymentMenu();
               case "3" -> CompetitionMenu();
               case "0" ->  goAgain = false;
               default -> ui.statusMessage(Status.INVALID_CHOICE);
        }}

        /*Member member = new Member("Torben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        Member member2 = new Member("Søren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10, 10, 1966);
        Member member3 = new Member("Tobias Vold", "98747723489", "Tobiases@mail.com", "TobyAllé 31", 1, 1, 1999);
        Member member4 = new Member("Finn Finsen", "8888888", "Finns@mail.com", "Finnminvej 99", 24, 9, 1920);
        Member member5 = new Member("Jim Henry", "67453219", "Jims@mail.com", "Jimminvej 89", 14, 6, 2008);
        System.out.println(memberList.calculateAge(member));
        System.out.println(member.getBirthDate());
        System.out.println(member.getMembershipNumber());
        memberList.addMember(member);
        memberList.addMember(member2);
        memberList.addMember(member3);
        memberList.addMember(member4);
        memberList.addMember(member5);*/

        //Henter members fra fil
        /*storedMembers = FileHandler.getMembersFromFile();
        sendStoredMembers(storedMembers);
        ui.printMemberLists(memberList.printMemberLists());*/

        System.out.println(calculateTotalIncome(memberList));

        try {
            FileHandler.storeMember(memberList.getMemberList());
        } catch (IOException e) {
            System.out.println("Failed to store members");
        }
    }

    public void CompetitionMenu(){
        boolean goAgain = true;
        while (goAgain){
        String choice = ui.getCompetitionMenu();
            switch (choice){
                case "1" -> System.out.println("a");//  Top 5 lister
                case "2" -> System.out.println("a"); //Registrer resultat
                case "3" -> System.out.println("a"); //Tilknyt disciplinx½
                case "0" -> System.out.println("a"); //Tilbage til hovedmenu
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }}
    }

    public void paymentMenu(){
        boolean goAgain = true;
        while (goAgain){
        String choice = ui.getPaymentsMenu();
            switch (choice){
                case "1" -> System.out.println("a");// Forventet indtjening
                case "2" -> System.out.println("a"); //Modtag betaling
                case "3" -> System.out.println("a"); //Vis restancer
                case "4" -> System.out.println("a"); //Opkræv kontingenter
                case "0" -> System.out.println("a"); //Tilbage til hovedmenu
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }}

    }

    public void memberMenu(){

        boolean goAgain = true;
        while (goAgain){
        String choice = ui.getMemberMenu();
        switch (choice){
            case "1" -> System.out.println("a");//find member
            case "2" -> createNewMember(ui.createNewMember());
            case "0" -> goAgain = false;
            default -> ui.statusMessage(Status.INVALID_CHOICE);
         }}
    }

    public double calculateTotalIncome(MemberList memberList){
        double totalSum = 0;
        for (int i = 0; i < memberList.getMemberList().size(); i++) {
            totalSum += calculateMembershipFee(memberList.getMemberList().get(i));
        }
        /*for (Member member : memberList) {
           totalSum += calculateMembershipFee(member);
        }*/
        return totalSum;
    }

    public double calculateMembershipFee(Member member){
        return calculation.calculateContingent(member.getAge(), member.getIsActive());
    }

    public void createNewMember(String[] memberInfo) {
        try{
            int day = Integer.parseInt(memberInfo[4]);
            int month = Integer.parseInt(memberInfo[5]);
            int year = Integer.parseInt(memberInfo[6]);

            StandardMember newMember = new StandardMember(memberInfo[0],memberInfo[1],memberInfo[2],memberInfo[3],day,month,year);
            memberList.addMember(newMember);
            ui.printMessage("Du har nu oprettet " + newMember + " som medlem i klubben.");

        } catch (NumberFormatException e){
            ui.printMessage("Ugyldigt input. Indtast venligst talværdier i fødselsdato-oplysninger.");
        }

    }

        //TODO generer et membershipnumber
    private void emailValidate(){
    }

    private void homeAddressValidate() {
    }

    public Member findMember(String userInputString){
        return memberList.findMember(userInputString);
    }

    public void statusCreation(){
    }

    public String userInputString() {
        return ui.userInputString();
    }

    public ArrayList<Member> memberList() {
        return memberList.getMemberList();
    }

    public void sendStoredMembers(ArrayList storedMembers){
        memberList.membersFromController(storedMembers);
        storedMembers.clear();
    }
}
