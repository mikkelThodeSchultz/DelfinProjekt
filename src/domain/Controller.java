//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain;

import file.FileHandler;
import org.javatuples.Quartet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import member.*;
import ui.Disciplines;
import ui.Status;
import ui.UserInterface;

public class Controller {
    private UserInterface ui = new UserInterface();
    private MemberList memberList = new MemberList();
    private Calculation calculation = new Calculation();
    private FileHandler FileHandler = new FileHandler();
    private ArrayList<Member> storedMembers;
    private ArrayList<Competition> listOfComps = new ArrayList<>();


    public Controller() {
    }

    public void start() {
        //Henter members fra fil
        storedMembers = FileHandler.getMembersFromFile();
        sendStoredMembers(storedMembers);
        listOfComps.addAll(FileHandler.getCompsFromFile());
        // ui.printMemberLists(memberList.printMemberLists());

        ui.getWelcomeMessage();
        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getMainMenu();
            switch (choice) {
                case "1" -> memberMenu();
                case "2" -> paymentMenu();
                case "3" -> CompetitionMenu();
                case "0" -> goAgain = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }


        StandardMember member = new StandardMember("Torben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        StandardMember member2 = new StandardMember("Søren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10, 10, 1966);
        StandardMember member3 = new StandardMember("Tobias Vold", "98747723489", "Tobiases@mail.com", "TobyAllé 31", 1, 1, 1999);
        StandardMember member4 = new StandardMember("Finn Finsen", "8888888", "Finns@mail.com", "Finnminvej 99", 24, 9, 1920);
        StandardMember member5 = new StandardMember("Jim Henry", "67453219", "Jims@mail.com", "Jimminvej 89", 14, 6, 2008);
        memberList.addMember(member);
        memberList.addMember(member2);
        memberList.addMember(member3);
        memberList.addMember(member4);
        memberList.addMember(member5);
        CompetitiveMember cmember = new CompetitiveMember("cTorben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        CompetitiveMember cmember2 = new CompetitiveMember("cSøren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10, 10, 1966);
        CompetitiveMember cmember3 = new CompetitiveMember("cTobias Vold", "98747723489", "Tobiases@mail.com", "TobyAllé 31", 1, 1, 1999);
        CompetitiveMember cmember4 = new CompetitiveMember("cFinn Finsen", "8888888", "Finns@mail.com", "Finnminvej 99", 24, 9, 1920);
        CompetitiveMember cmember5 = new CompetitiveMember("cJim Henry", "67453219", "Jims@mail.com", "Jimminvej 89", 14, 6, 2008);
        memberList.addMember(cmember);
        memberList.addMember(cmember2);
        memberList.addMember(cmember3);
        memberList.addMember(cmember4);
        memberList.addMember(cmember5);
        Trainer tmember = new Trainer("tTorben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        Trainer tmember2 = new Trainer("tSøren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10, 10, 1966);
        Trainer tmember3 = new Trainer("tTobias Vold", "98747723489", "Tobiases@mail.com", "TobyAllé 31", 1, 1, 1999);
        Trainer tmember4 = new Trainer("tFinn Finsen", "8888888", "Finns@mail.com", "Finnminvej 99", 24, 9, 1920);
        Trainer tmember5 = new Trainer("tJim Henry", "67453219", "Jims@mail.com", "Jimminvej 89", 14, 6, 2008);
        memberList.addMember(tmember);
        memberList.addMember(tmember2);
        memberList.addMember(tmember3);
        memberList.addMember(tmember4);
        memberList.addMember(tmember5);


        Competition comp = new Competition("Test");
        Competition comp2 = new Competition("Test2");
        CompetitonResult result = new CompetitonResult("cskr6795",60.0,2,Disciplines.BACK_CRAWL.toString());
        comp.addResult(result);
        comp2.addResult(result);
        ArrayList<Competition> comps = new ArrayList<>();
        comps.add(comp);
        comps.add(comp2);



        try {
            FileHandler.storeData(memberList.getMemberList(), comps);
        } catch (IOException e) {
            System.out.println("Failed to store members");
        }
    }

    public void CompetitionMenu() {
        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getCompetitionMenu();
            switch (choice) {
                case "1" -> System.out.println("a");//  Top 5 lister
                case "2" -> System.out.println("a"); //Registrer resultat
                case "3" -> System.out.println("a"); //Tilknyt disciplinx½
                case "0" -> goAgain = false; //Tilbage til hovedmenu
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

    public void paymentMenu() {
        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getPaymentsMenu();
            switch (choice) {
                case "1" -> System.out.println(calculateTotalIncome());// Forventet indtjening
                case "2" -> System.out.println("a"); //Modtag betaling
                case "3" -> ui.printMessage(listOfMembersWhoOwe().toString()); //Todo flot udprintning på listen
                case "4" -> System.out.println("a"); //Opkræv kontingenter
                case "0" -> goAgain = false; //Tilbage til hovedmenu
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }

    }

    public void memberMenu() {

        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getMemberMenu();
            switch (choice) {
                case "1" -> ui.showMemberList(memberList.printMemberList());
                case "2" -> findMember(ui.findSpecificMemberMenu());
                case "3" -> createNewMember(ui.createNewMember());
                case "0" -> goAgain = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

   /* public void foundMemberMenu(){
        boolean goAgain = true;
        while(goAgain){
            String choice = ui.getFoundMemberMenu();
            switch (choice){
                case "1" -> editName();
                case "2" -> editAddress();
                case "3" -> editEmail();
                case "4" -> editPhoneNumber();
                case "5" -> editBirthDate();
                case "6" -> editMembershipStatus();
                case "7" -> editLevel();
                case "8" -> deleteMember();
                case "0" -> goAgain = false;
            }
        }
    }*/

    public void editName(String userInputString){

        ui.printMessage("Rediger navnet her: ");
        String oldName = memberList.editName(userInputString);
        ui.printMessage("Du har nu ændret " + oldName + " til " + userInputString+".");

    }


    public double calculateTotalIncome() {
        double totalSum = calculation.calculateContingentForMultipleMembers(memberList.getMemberList());
        return totalSum;
    }

    public ArrayList<Member> listOfMembersWhoOwe() {
        return calculation.listOfMembersWhoOwe(memberList.getMemberList());
    }

    public double calculateMembershipFee(Member member) { //Hvis calculateContingent bliver gjort private, skal denne laves om
        return calculation.calculateContingent(member.getAge(), member.getIsActive());
    }

    public void createNewMember(String[] memberInfo) {
        try {
            int day = Integer.parseInt(memberInfo[4]);
            int month = Integer.parseInt(memberInfo[5]);
            int year = Integer.parseInt(memberInfo[6]);

            StandardMember newMember = new StandardMember(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3], day, month, year);

            memberList.addMember(newMember);
            ui.printMessage("Du har nu oprettet " + newMember + " som medlem i klubben.");

        } catch (NumberFormatException e) {
            ui.printMessage("Ugyldigt input. Indtast venligst talværdier i fødselsdato-oplysninger.");
        }
    }

    public Member[] findMember(String userInputString) {
        String search = userInputString;
        Member[] foundMembers = memberList.findMember(search);
        String members = "";
        if (foundMembers.length == 1) {
            memberList.setSelectedMember(foundMembers[0]);
        } else if (foundMembers.length > 1) {
            int counter = 0;
            for (int i = 0; i < foundMembers.length; i++) {
                counter++;
                members += counter + " " + foundMembers[i] + "\n";
            }
            ui.printMessage(members);
            int select = 0;
            while (select < 1 || select > foundMembers.length) {
                select = ui.userInputInt();
                if (select < 1 || select > foundMembers.length) {
                    ui.printMessage("Vælg venligst tallet ud for det ønskede medlem.");
                } else {
                    memberList.setSelectedMember(foundMembers[select - 1]);
                }
            }
        } else {
            ui.printMessage("Der er ingen medlemmer der passer til dine søgekriterier.");
            memberList.setSelectedMember(null);
        }

        return memberList.findMember(userInputString);
    }

    public void statusCreation() {
    }

    public String userInputString() {
        return ui.userInputString();
    }

    public ArrayList<Member> memberList() {
        return memberList.getMemberList();
    }

    public void sendStoredMembers(ArrayList<Member> storedMembers) {
        memberList.membersFromController(storedMembers);
        //storedMembers.clear();
    }
}
