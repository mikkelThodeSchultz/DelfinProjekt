//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain;

import file.FileHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import member.*;
import ui.Disciplines;
import ui.Status;
import ui.UserInterface;

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
                case "8" -> SaveCurrent();
                case "9" -> clearJson();
                case "0" -> goAgain = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }

            generateTestData();

        try {
            FileHandler.storeData(memberList.getMemberList(), listOfComps);
        } catch (IOException e) {
            System.out.println("Failed to store members");
        }
    }


    public void SaveCurrent() {
        try {
            FileHandler.storeData(memberList.getMemberList(), listOfComps);
        } catch (IOException e) {
            System.out.println("Failed to store members");
        }
    }

    public void clearJson() {
        MemberList memList = new MemberList();
        ArrayList<Competition> compList = new ArrayList<>();

        try {
            FileHandler.storeData(memList.getMemberList(), compList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CompetitionMenu() {
        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getCompetitionMenu();
            switch (choice) {
                case "1" -> topFive();//  Top 5 lister
                case "2" -> createCompOrRegResult(); //Registrer resultat og opret konkurrence
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
                case "2" -> editMemberMenu();
                case "3" -> createMemberMenu();
                case "0" -> goAgain = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

    public void editMemberMenu() {
        boolean goAgain = true;
        findMember(ui.findSpecificMemberMenu());
        while (goAgain) {
            String choice = ui.getFoundMemberMenu();
            switch (choice) {
                case "1" -> editName();
                case "2" -> editAddress();
                case "3" -> editEmail();
                case "4" -> editPhoneNumber();
                case "5" -> editBirthDate();
                case "6" -> editMembershipStatus();
                //case "7" -> editLevel();
                case "8" -> deleteMember();
                case "0" -> goAgain = false;
            }
        }
    }

    public void editName() {
        ui.printMessage("Rediger navnet her: ");
        String userInput = ui.userInputString();
        String oldName = memberList.editName(userInput);
        ui.changeMessage(oldName, userInput);
    }

    public void editAddress() {
        ui.printMessage("Rediger adressen her: ");
        String userInput = ui.userInputString();
        String oldAddress = memberList.editAddress(userInput);
        ui.changeMessage(oldAddress, userInput);
    }

    public void editEmail() {
        ui.printMessage("Rediger e-mail adressen her: ");
        String userInput = ui.userInputString();
        String oldEmail = memberList.editEmail(userInput);
        ui.changeMessage(oldEmail, userInput);

    }

    public void editPhoneNumber() {
        ui.printMessage("Rediger telefonummeret her: ");
        String userInput = ui.userInputString();
        String oldNumber = memberList.editPhoneNumber(userInput);
        ui.changeMessage(oldNumber, userInput);
    }

    public void editBirthDate() {
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
        } catch (NumberFormatException e) {
            ui.printMessage("Skriv venligst en dato i feltet.\n");
        }
    }

    public void editMembershipStatus() {
        memberList.editMembershipStatus();
        ui.printMessage(memberList.isActiveAsString());
    }

    public void deleteMember() {
        ui.showMemberList(memberList.printMemberList());
        memberList.removeMember();
        ui.printMessage("Du har nu slettet medlemmet");
    }

    public void createMemberMenu() {
        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getCreateMemberMenu();
            switch (choice) {
                case "1", "2", "3" -> createNewMember(ui.createNewMember(), choice);
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

            if (choice.equals("1")) {
                StandardMember newMember = new StandardMember(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3], day, month, year);
                memberList.addMember(newMember);
                ui.printMessage("Du har nu oprettet " + newMember + " som medlem i klubben.");
            } else if (choice.equals("2")) {
                CompetitiveMember newMember = new CompetitiveMember(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3], day, month, year);
                memberList.addMember(newMember);
                ui.printMessage("Du har nu oprettet " + newMember + " som medlem i klubben.");
            } else if (choice.equals("3")) {
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
            boolean goAgain = true;
            while (goAgain) {
                ui.printMessage("Vælg venligst tallet ud for det ønskede medlem eller tryk '0' for at vende tilbage til medlemsmenuen \n");
                String selection = "";
                selection = ui.userInputString();
                int select = Integer.parseInt(selection);
                if(select > 0 && select < foundMembers.length){
                    goAgain = false;
                }
                if (select == 0) {
                    goAgain = false;
                } else {
                    try {
                        memberList.setSelectedMember(foundMembers[select - 1]);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printMessage("Dit svin!");
                    }
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
            Disciplines disipline = ui.pickDisipline();
            if (disipline != null) {


                ArrayList<CompetitonResult> results = new ArrayList<>();
                ArrayList<CompetitonResult> resultsInDisipline = new ArrayList<>();


                ArrayList<CompetitiveMember> compMem = getCompMembers();
                ArrayList<CompetitiveMember> compMemToRemove = new ArrayList<>();
                if (isJunior.equals("1")) {
                    for (CompetitiveMember mem : compMem) {
                        if (mem.getAge() > 18) {
                            compMemToRemove.add(mem);
                        }
                    }
                } else {
                    for (CompetitiveMember mem : compMem) {
                        if (mem.getAge() < 18) {
                            compMemToRemove.add(mem);
                        }
                    }
                }
                compMem.removeAll(compMemToRemove);


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

    public void createCompOrRegResult() {


        boolean keepGoing = true;
        while (keepGoing) {
            ui.compAndResultMenu();
            String choice = userInputString();
            int memChoice = 0;
            if (choice.equals("1")) {

                ui.printMessage("Hvad hedder konkurrencen?\n");
                String compName = userInputString();
                Competition comp = new Competition(compName);
                ui.printMessage("Konkurrence med navn " + compName + " er oprettet\n");
                listOfComps.add(comp);

            } else if (choice.equals("2")) {
                ui.printMessage("Vælg en konkurrence \n");

                int compChoice = getCompChoice();


                if (compChoice != -1) {

                    ui.printMessage("Du skal vælge et medlem \n");
                    ui.searchOrMemberList();
                    String memberSearchChoice = userInputString();
                    ArrayList<Member> chosenMember = new ArrayList<>();

                    if (memberSearchChoice.equals("1")) {
                        chosenMember.add(getMemberFromSearch());
                    } else if (memberSearchChoice.equals("2")) {
                        memChoice = getMemberFromList(chosenMember);
                    } else if (memberSearchChoice.equals("0")) {
                        memChoice = -1;
                    }

                    if (memChoice != -1) {

                        Disciplines discipline = ui.pickDisipline();

                        ui.printMessage("Hvilken placering fik deltageren\n");
                        int placement = Integer.parseInt(userInputString());
                        ui.printMessage("Hvilken tid fik deltageren\n");
                        double time = Double.parseDouble(userInputString());

                        CompetitonResult result = new CompetitonResult(chosenMember.get(0).getMembershipNumber(), time, placement, discipline.toString());
                        listOfComps.get(compChoice).addResult(result);
                        ui.printMessage("Et resultat er blevet registeret med info \nID: " + result.getMemberID() +
                                "\nDisiplin: " + result.getDiscipline() +
                                "\nTid: " + result.getTime() +
                                "\nPlacering: " + result.getRank() + "\n");
                    }
                }

            } else if (choice.equals("0")) {
                keepGoing = false;
            } else {
                ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

    private int getMemberFromList(ArrayList<Member> chosenMember) {
        int memChoice = 0;

        boolean badChoice = true;
        ArrayList<CompetitiveMember> memList = getCompMembers();
        while (badChoice) {
            int count = 1;
            for (Member mem : memList) {
                ui.printMessage(count + ") - " + mem.getName() + "\n");
                count++;
            }
            memChoice = Integer.parseInt(userInputString());
            memChoice -= 1;

            if (memChoice < memList.size()) {
                badChoice = false;
                chosenMember.add(memList.get(memChoice));
            } else {
                ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
        return memChoice;
    }

    private Member getMemberFromSearch() {
        int memChoice = 0;
        ArrayList<Member> chosenMember = new ArrayList<>();
        boolean badChoice = true;
        Member[] memberSearchResult = findMember(ui.findSpecificMemberMenu());
        chosenMember.add(memberSearchResult[0]);
        userInputString();
        return chosenMember.get(0);
    }

    private int getCompChoice() {
        boolean badChoice = true;

        int compChoice = 0;
        while (badChoice) {
            int count = 1;
            for (Competition comps : listOfComps) {
                ui.printMessage(count + ") - " + comps.getCompetitionName() + "\n");
                count++;
            }

            ui.printMessage("0) - Tilbage\n");
            compChoice = Integer.parseInt(userInputString());
            compChoice -= 1;
            if (compChoice < listOfComps.size()) {
                badChoice = false;
            } else {
                ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
        return compChoice;
    }

    public ArrayList<CompetitiveMember> getCompMembers() {
        ArrayList<CompetitiveMember> compMem = new ArrayList<>();
        for (Member mem : memberList.getMemberList()) {
            if (mem instanceof CompetitiveMember) {
                compMem.add((CompetitiveMember) mem);
            }
        }
        return compMem;
    }

    private void generateTestData() {
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
        compMem.get(5).addBestTrainingResult(now, 41, Disciplines.BACK_CRAWL);
    }

}
