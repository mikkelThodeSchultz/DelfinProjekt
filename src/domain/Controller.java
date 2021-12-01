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
import java.util.*;

import member.*;
import ui.Disciplines;
import ui.Status;
import ui.UserInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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


        ArrayList<CompetitiveMember> compMem = new ArrayList<>();
        for (Member mem : memberList.getMemberList()) {
            if (mem instanceof CompetitiveMember) {
                compMem.add((CompetitiveMember) mem);
            }
        }

        Competition comp = new Competition("Test");
        Competition comp2 = new Competition("Test2");
        listOfComps.add(comp);
        CompetitonResult result = new CompetitonResult("cskr6795", 60.0, 2, Disciplines.BACK_CRAWL.toString());
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 55.0, 2, Disciplines.BACK_CRAWL.toString());
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 50.0, 2, Disciplines.BACK_CRAWL.toString());
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 40.0, 2, Disciplines.BACK_CRAWL.toString());
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 30.0, 2, Disciplines.BACK_CRAWL.toString());
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 20, 2, Disciplines.BACK_CRAWL.toString());
        comp.addResult(result);
        comp2.addResult(result);

        // TEST DATA
        LocalDateTime now = LocalDateTime.now();
        compMem.get(0).addBestTrainingResult(now, 20, Disciplines.BACK_CRAWL);
        compMem.get(0).addBestTrainingResult(now, 20, Disciplines.CRAWL);
        compMem.get(1).addBestTrainingResult(now, 25, Disciplines.BACK_CRAWL);
        compMem.get(2).addBestTrainingResult(now, 30, Disciplines.BACK_CRAWL);
        compMem.get(3).addBestTrainingResult(now, 35, Disciplines.BACK_CRAWL);
        compMem.get(4).addBestTrainingResult(now, 40, Disciplines.BACK_CRAWL);
        // compMem.get(5).addBestTrainingResult(now, 41, Disciplines.BACK_CRAWL);


        try {
            FileHandler.storeData(memberList.getMemberList(), listOfComps);
        } catch (IOException e) {
            System.out.println("Failed to store members");
        }
    }

    public void CompetitionMenu() {
        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getCompetitionMenu();
            switch (choice) {
                case "1" -> topFive();//  Top 5 lister
                case "2" -> System.out.println("a"); //Registrer resultat
                case "3" -> System.out.println("a"); //Tilknyt disciplin
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
                case "3" -> createMemberMenu();
                case "0" -> goAgain = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

    public void foundMemberMenu(){
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
               // case "8" -> deleteMember();
                case "0" -> goAgain = false;
            }
        }
    }

    public void editName(){
        ui.printMessage("Rediger navnet her: ");
        String userInput = ui.userInputString();
        String oldName = memberList.editName(userInput);
        ui.changeMessage(oldName,userInput);
    }

    public void editAddress(){
        ui.printMessage("Rediger adressen her: ");
        String userInput = ui.userInputString();
        String oldAddress = memberList.editAddress(userInput);
        ui.changeMessage(oldAddress,userInput);
    }

    public void editEmail(){
        ui.printMessage("Rediger e-mail adressen her: ");
        String userInput = ui.userInputString();
        String oldEmail = memberList.editEmail(userInput);
        ui.changeMessage(oldEmail,userInput);

    }

    public void editPhoneNumber(){
        ui.printMessage("Rediger telefonummeret her: ");
        String userInput = ui.userInputString();
        String oldNumber = memberList.editPhoneNumber(userInput);
        ui.changeMessage(oldNumber,userInput);
    }

    public void editBirthDate(){
        try {
            ui.printMessage("Rediger fødselsdag her: ");
            int day = Integer.parseInt(ui.userInputString());
            ui.printMessage("Rediger fødselsmåned her: ");
            int month = Integer.parseInt(ui.userInputString());
            ui.printMessage("Rediger fødselsår her: ");
            int year = Integer.parseInt(ui.userInputString());
            LocalDate newBirthDate = LocalDate.of(year, month, day);
            String oldDate = memberList.editBirthDate(newBirthDate);
            String newDate = memberList.newDateToString(newBirthDate);
            ui.changeMessage(oldDate, newDate);
        } catch (NumberFormatException e){
            ui.printMessage("Skriv venligst en dato i feltet.\n");
        }
    }

    public void editMembershipStatus(){
        memberList.editMembershipStatus();
        ui.printMessage(memberList.isActiveAsString());
    }

    public void editLevel(){

    }

    public void deleteMember(){

    }

    public void createMemberMenu(){
        boolean goAgain = true;
        while (goAgain){
            String choice = ui.getCreateMemberMenu();
            switch (choice){
                case "1", "2", "3" -> createNewMember(ui.createNewMember(),choice);
                case "0" -> goAgain = false;
            }
        }
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

    public void createNewMember(String[] memberInfo, String choice) {
        try {
            int day = Integer.parseInt(memberInfo[4]);
            int month = Integer.parseInt(memberInfo[5]);
            int year = Integer.parseInt(memberInfo[6]);

            if (choice.equals("1")){
                StandardMember newMember = new StandardMember(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3], day, month, year);
                memberList.addMember(newMember);
                ui.printMessage("Du har nu oprettet " + newMember + " som medlem i klubben.");
            } else if (choice.equals("2")){
                CompetitiveMember newMember = new CompetitiveMember(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3], day, month, year);
                memberList.addMember(newMember);
                ui.printMessage("Du har nu oprettet " + newMember + " som medlem i klubben.");
            } else if (choice.equals("3")){
                Trainer newTrainer = new Trainer(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3], day, month, year);
                memberList.addMember(newTrainer);
                ui.printMessage("Du har nu oprettet " + newTrainer + " som medlem i klubben.");
            }
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
                    foundMemberMenu();
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

    public void topFive() {
        ui.isJunior();
        String isJunior = ui.userInputString();
        ui.topFiveMenu();
        String IsTraining = userInputString();

        if (!IsTraining.equals("0")) {
            Disciplines disipline = ui.topFiveDisipline();
            if (disipline != null) {


                ArrayList<CompetitonResult> results = new ArrayList<>();
                ArrayList<CompetitonResult> resultsInDisipline = new ArrayList<>();


                ArrayList<CompetitiveMember> compMem = new ArrayList<>();
                if (isJunior.equals("1")) {
                    for (Member mem : memberList.getMemberList()) {
                        if (mem instanceof CompetitiveMember && mem.getAge() < 18) {
                            compMem.add((CompetitiveMember) mem);
                        }
                    }
                } else {
                    for (Member mem : memberList.getMemberList()) {
                        if (mem instanceof CompetitiveMember && mem.getAge() > 18) {
                            compMem.add((CompetitiveMember) mem);
                        }
                    }
                }


                //PRINTER KONKURRENCE RESULTATER


                if (IsTraining.equals("1")) {


                    for (Competition comps : listOfComps) {
                        results.addAll(comps.getResults());
                    }
                    for (CompetitonResult compRes : results) {
                        if (compRes.getDiscipline().equals(disipline.toString())) {
                            String memberIDs = compRes.getMemberID();
                            for (CompetitiveMember compMember : compMem) {
                                if (memberIDs.equals(compMember.getMembershipNumber())) {
                                    resultsInDisipline.add(compRes);
                                }
                            }
                        }
                    }

                    resultsInDisipline.sort(Comparator.comparing(CompetitonResult::getTime));

                    if (resultsInDisipline.size() < 5) {
                        for (int i = 0; i < resultsInDisipline.size(); i++) {
                            String memberID = resultsInDisipline.get(i).getMemberID();
                            double time = resultsInDisipline.get(i).getTime();
                            ui.printMessage("MedlemsID: " + memberID + "  -  Tid: " + time + "\n");
                        }
                    } else {
                        for (int i = 0; i < 5; i++) {
                            String memberID = resultsInDisipline.get(i).getMemberID();
                            double time = resultsInDisipline.get(i).getTime();
                            ui.printMessage("MedlemsID: " + memberID + "  -  Tid: " + time + "\n");
                        }
                    }


                    //PRINTER TRÆNING RESULTATER

                } else if (IsTraining.equals("2")) {

                    HashMap<Double, String> trainResult = new HashMap<>();
                    for (CompetitiveMember actMem : compMem) {

                        for (BestTrainingResult trainRes : actMem.getBestTrainingResults()) {
                            if (trainRes.getDiscipline().toString().equals(disipline.toString())) {
                                trainResult.put(trainRes.getTime(), actMem.getMembershipNumber());
                            }
                        }
                    }

                    TreeMap<Double, String> sorted = new TreeMap<>(trainResult);
                    sorted.putAll(trainResult);
                    if (sorted.size() < 5) {
                        for (int i = 0; i < sorted.size(); i++) {
                            String memberID = sorted.firstEntry().getValue();
                            double time = sorted.firstKey();
                            ui.printMessage("MedlemsID: " + memberID + "  -  Tid: " + time + "\n");
                            sorted.remove(sorted.firstKey());

                        }
                    } else {
                        for (int i = 0; i < 5; i++) {
                            String memberID = sorted.firstEntry().getValue();
                            double time = sorted.firstKey();
                            ui.printMessage("MedlemsID: " + memberID + "  -  Tid: " + time + "\n");
                            sorted.remove(sorted.firstKey());
                        }
                    }
                }
            }
        }
    }
}
