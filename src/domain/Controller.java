//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain;

import file.FileHandler;

import java.io.IOException;
import java.time.DateTimeException;
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
    private FileHandler fileHandler = new FileHandler();
    private ArrayList<Competition> listOfComps = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public Controller() {
    }

    public void start() {
        loadDataFromFile();
        ui.getWelcomeMessage();

        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getMainMenu();
            switch (choice) {
                case "1" -> memberMenu();
                case "2" -> paymentMenu();
                case "3" -> trainerMenu();
                //case "7" -> createNewUser();
                case "7" -> generateTestData();
                case "8" -> saveCurrent();
                case "9" -> clearJson();
                case "0" -> goAgain = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
        saveCurrent();
    }

    private void loadDataFromFile() {
        memberList.membersFromController(fileHandler.getMembersFromFile());
        listOfComps.addAll(fileHandler.getCompsFromFile());
        users.addAll(fileHandler.getUsersFromFile());
    }

    private void saveCurrent() {
        try {
            fileHandler.storeData(memberList.getMemberList(), listOfComps, users);
        } catch (IOException e) {
            System.out.println("Failed to store members");
        }
    }

    private void clearJson() {
        MemberList memList = new MemberList();
        ArrayList<Competition> compList = new ArrayList<>();
        ArrayList<User> userList = new ArrayList<>();
        memberList = memList;
        listOfComps = compList;
        try {
            fileHandler.storeData(memList.getMemberList(), compList, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void trainerMenu() {
        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.TrainerMenu();
            switch (choice) {
                case "1" -> topFive();//  Top 5 lister
                case "2" -> competitionMenu(); //Registrer resultat og opret konkurrence
                case "3" -> teamMenu(); //Tilknyt disciplin
                case "4" -> setDisciplineOfSwimmer();
                case "5" -> trainingResultMenu();
                case "0" -> goAgain = false; //Tilbage til hovedmenu
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

    private void paymentMenu() {
        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getPaymentsMenu();
            switch (choice) {
                case "1" -> ui.printMessage("Forventet indtjening er: " + calculateTotalIncome() + " kr");
                case "2" -> setMembershipToHasPayed(); //Modtag betaling
                case "3" -> ui.printMessage(listOfMembersWhoOweAsString()); //Liste af medlemmer der skylder penge
                case "4" -> demandPayment(); //Opkræv kontingenter
                case "0" -> goAgain = false; //Tilbage til hovedmenu
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

    private void memberMenu() {

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

    private void editMemberMenu() {
        boolean goAgain = true;
        findMember(ui.findSpecificMemberMenu());
        while (goAgain) {
            if (memberList.getSelectedMember() == null) {
                goAgain = false;
            } else {
                ui.printMessage(memberList.collectAllInfoString());
                String choice = ui.getFoundMemberMenu();
                switch (choice) {
                    case "1" -> searchAfterMemberAgain();
                    case "2" -> editName();
                    case "3" -> editAddress();
                    case "4" -> editEmail();
                    case "5" -> editPhoneNumber();
                    case "6" -> editBirthDate();
                    case "7" -> editMembershipStatus();
                    case "8" -> editLevel();
                    case "9" -> deleteMember();
                    case "0" -> {
                        memberList.setSelectedMember(null);
                        goAgain = false;
                    }
                }
            }
        }
    }

    private void editLevel() {
        ui.printMessage("Nuværende svømmeniveau: " + memberList.getSelectedMemberRole());
        Member member = memberList.getSelectedMember();
        String memberType = "";
        String choice = "";
        String memberShipNumber = memberList.getSelectedMember().getMembershipNumber();
        String day = String.valueOf(member.getBirthDate().getDayOfMonth());
        String month = String.valueOf(member.getBirthDate().getMonthValue());
        String year = String.valueOf(member.getBirthDate().getYear());
        String[] memberInfo = {member.getName(), member.getPhoneNumber(), member.getEmail(), member.getHomeAddress(), day, month, year};
        if (member instanceof StandardMember) {
            ui.printMessage("\nSkal medlemmet ændres til (1) Konkurrencesvømmer eller (2) Træner?\n");
            choice = ui.userInputString();
            switch (choice) {
                case "1" -> memberType = "2";
                case "2" -> memberType = "3";
                default -> ui.printMessage("Indtast venligst et 1 eller 2");
            }
        } else if (member instanceof CompetitiveMember) {
            ui.printMessage("Skal medlemmet ændres til (1) Standardmedlem eller (2) Træner?");
            choice = ui.userInputString();
            switch (choice) {
                case "1" -> memberType = "1";
                case "2" -> memberType = "3";
                default -> ui.printMessage("Indtast venligst et 1 eller 2");
            }
        } else if (member instanceof Trainer) {
            ui.printMessage("Skal medlemmet ændres til (1) Standardmedlem eller (2) Konkurrencesvømmer?");
            choice = ui.userInputString();
            switch (choice) {
                case "1" -> memberType = "1";
                case "2" -> memberType = "2";
                default -> ui.printMessage("Indtast venligst et 1 eller 2");
            }
        }
        memberList.removeMember();
        memberList.setSelectedMember(createNewMember(memberInfo, memberType));
        memberList.getSelectedMember().setMembershipNumber(memberShipNumber);
    }

    private void editName() {
        ui.printMessage("Nuværende navn: " + memberList.getSelectedMemberName());
        ui.printMessage("\nRediger navnet her. Tryk 0 for at afbryde");
        String userInput = ui.userInputString();
        if (!userInput.equals("0")) {
            String oldName = memberList.editName(userInput);
            ui.changeMessage(oldName, userInput);
        }
    }

    private void editAddress() {
        ui.printMessage("Nuværende adresse: " + memberList.getSelectedMemberAddress());
        ui.printMessage("\nRediger adressen her. Tryk 0 for at afbryde");
        String userInput = ui.userInputString();
        if (!userInput.equals("0")) {
            String oldAddress = memberList.editAddress(userInput);
            ui.changeMessage(oldAddress, userInput);
        }
    }

    private void editEmail() {
        ui.printMessage("Nuværende e-mail: " + memberList.getSelectedMemberEmail());
        ui.printMessage("\nRediger e-mail adressen her. Tryk 0 for at afbryde");
        String userInput = ui.userInputString();
        if (!userInput.equals("0")) {
            String oldEmail = memberList.editEmail(userInput);
            ui.changeMessage(oldEmail, userInput);
        }
    }

    private void editPhoneNumber() {
        ui.printMessage("Nuværende telefonnummer: " + memberList.getSelectedMemberPhoneNumber());
        ui.printMessage("\nRediger telefonnummeret her. Tryk 0 for at afbryde");
        String userInput = ui.userInputString();
        if (!userInput.equals("0")) {
            String oldNumber = memberList.editPhoneNumber(userInput);
            ui.changeMessage(oldNumber, userInput);
        }
    }

    private void editBirthDate() {
        ui.printMessage("Nuværende fødselsdato: " + memberList.getSelectedMemberBirthDate());
        try {
            ui.printMessage("Indtast fødselsdagsoplysninger (tryk 0 for at afbryde når som helst): ");
            ui.printMessage("Dag: ");
            int day = ui.userInputInt();
            if (day != 0) {
                ui.printMessage("Måned: ");
                int month = ui.userInputInt();
                if (month != 0) {
                    ui.printMessage("År: ");
                    int year = ui.userInputInt();
                    boolean wrongYear = true;
                    while (wrongYear) {
                        if (year > LocalDate.now().getYear()) {
                            ui.printMessage("Du har valgt et år i fremtiden, vælg venligst et rigtigt år: ");
                            year = ui.userInputInt();
                        } else {
                            wrongYear = false;
                        }
                    }
                    if (year != 0) {
                        LocalDate newBirthDate = LocalDate.of(year, month, day);
                        String oldDate = memberList.editBirthDate(newBirthDate);
                        String newDate = memberList.newDateToString(newBirthDate);
                        ui.changeMessage(oldDate, newDate);
                    }
                }
            }
        } catch (NumberFormatException e) {
            ui.printMessage("Skriv venligst et tal i feltet.\n");
        } catch (DateTimeException e) {
            ui.printMessage("Skriv venligst en gyldig dato i feltet.\n");
        }
    }

    private void editMembershipStatus() {
        ui.printMessage("Nuværende medlemskabsstatus: " + memberList.getSelectedMembershipStatus());
        ui.printMessage("\nVil du ændre medlemsstatus for " + memberList.getSelectedMember() + ": ja(j) eller nej(n)?\n");
        if (ui.userInputString().equalsIgnoreCase("j")) {
            memberList.editMembershipStatus();
            ui.printMessage(memberList.isActiveAsString());
        } else {
            ui.statusMessage(Status.ANNULLERET);
        }
    }

    private void deleteMember() {
        ui.printMessage("Vil du slette medlemmet: " + memberList.getSelectedMemberName() + " ja(j) eller nej(n)?\n");
        if (ui.userInputString().equalsIgnoreCase("j")) {
            memberList.removeMember();
            ui.printMessage("Du har nu slettet medlemmet: " + memberList.getSelectedMemberName() + "\n");
            memberList.setSelectedMember(null);
        } else {
            ui.statusMessage(Status.ANNULLERET);
        }
    }

    private void createMemberMenu() {
        boolean goAgain = true;
        while (goAgain) {
            String choice = ui.getCreateMemberMenu();
            switch (choice) {
                case "1", "2", "3" -> createNewMember(ui.createNewMember(), choice);
                case "0" -> goAgain = false;
            }
        }
    }

    private double calculateTotalIncome() {
        return calculation.calculateContingentForMultipleMembers(memberList.getMemberList());
    }

    private ArrayList<Member> listOfMembersWhoOwe() {
        return calculation.listOfMembersWhoOwe(memberList.getMemberList());
    }

    private String listOfMembersWhoOweAsString() {
        String listAsString = "";
        for (int i = 0; i < listOfMembersWhoOwe().size(); i++) {
            listAsString += listOfMembersWhoOwe().get(i).getName();
            listAsString += " ";
            listAsString += listOfMembersWhoOwe().get(i).getMembershipNumber();
            listAsString += "\n";
        }
        return listAsString;
    }

    private void demandPayment() {
        calculation.demandPayment(memberList.getMemberList());
        ui.printMessage("Alle medlemmer er nu opdateret til at skylde kontingent");
    }

    private void setMembershipToHasPayed() {
        boolean goAgain = true;
        while (goAgain) {
            ui.printMessage("Skriv en del af, eller hele navnet på det medlem du ønsker at finde: \n");
            String temp = ui.userInputString();
            findMember(temp);
            Member member = memberList.getSelectedMember();
            if (member != null) {
                boolean payedOrNot = calculation.setMembershipPayedStatusToReverse(member);
                if (payedOrNot) {
                    ui.printMessage(member + " er nu registreret til at have betalt ");
                } else {
                    ui.printMessage(member + " er nu registreret til ikke at have betalt ");
                }
            }
            memberList.setSelectedMember(null);
            goAgain = false;
        }
    }

    private Member createNewMember(String[] memberInfo, String choice) {
        Member member = null;
        boolean hasAllInput = true;
        for (String info : memberInfo) {
            if (info.equals("0")) {
                hasAllInput = false;
            }
        }
        if (hasAllInput) {
            try {
                int day = Integer.parseInt(memberInfo[4]);
                int month = Integer.parseInt(memberInfo[5]);
                int year = Integer.parseInt(memberInfo[6]);
                boolean wrongDate = true;
                while (wrongDate) {
                    if (year > LocalDate.now().getYear()) {
                        ui.printMessage("Du har valgt et år i fremtiden, vælg venligst et rigtigt år: ");
                        year = ui.userInputInt();
                    } else {
                        wrongDate = false;
                    }
                }
                switch (choice) {
                    case "1" -> {
                        StandardMember newMember = new StandardMember(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3], day, month, year);
                        memberList.addMember(newMember);
                        ui.printMessage("Du har nu oprettet " + newMember.getName() + " som medlem i klubben.");
                        member = newMember;
                    }
                    case "2" -> {
                        CompetitiveMember newCompMember = new CompetitiveMember(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3], day, month, year);
                        memberList.addMember(newCompMember);
                        ui.printMessage("Du har nu oprettet " + newCompMember.getName() + " som medlem i klubben.");
                        member = newCompMember;
                    }
                    case "3" -> {
                        Trainer newTrainer = new Trainer(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3], day, month, year);
                        memberList.addMember(newTrainer);
                        ui.printMessage("Du har nu oprettet " + newTrainer.getName() + " som medlem i klubben.");
                        member = newTrainer;
                    }
                }
            } catch (NumberFormatException e) {
                ui.printMessage("Ugyldigt input. Indtast venligst talværdier i fødselsdato-oplysninger.");
            } catch (DateTimeException e) {
                ui.printMessage("Indtast venligst en gyldig dato.");
            }
        }
        return member;
    }

    private Member[] findMember(String userInputString) {
        String search = userInputString;
        Member[] foundMembers = memberList.findMember(search);
        String members = "";
        if (foundMembers.length == 1) {
            memberList.setSelectedMember(foundMembers[0]);
            ui.printMessage("Du har valgt " + foundMembers[0] + "\n");
        } else if (foundMembers.length > 1) {
            int counter = 0;
            for (int i = 0; i < foundMembers.length; i++) {
                counter++;
                members += counter + " " + foundMembers[i] + "\n";
            }
            ui.printMessage(members);
            boolean goAgain = true;
            while (goAgain) {
                ui.printMessage("\nVælg venligst tallet ud for det ønskede medlem eller tryk '0' for at vende tilbage  \n");
                String selection = "";
                int select = -1;
                try {
                    selection = ui.userInputString();
                    select = Integer.parseInt(selection);
                } catch (NumberFormatException f) {
                }
                if (select > 0 && select <= foundMembers.length) {
                    goAgain = false;
                }
                if (select == 0) {
                    goAgain = false;
                } else {
                    try {
                        memberList.setSelectedMember(foundMembers[select - 1]);
                        ui.printMessage("Du har valgt " + foundMembers[select - 1] + "\n");
                    } catch (IndexOutOfBoundsException e) {
                        ui.printMessage("Indtast et tal imellem 1 og " + foundMembers.length + "\n");
                    }
                }
            }
        } else {
            ui.printMessage("Der er ingen medlemmer der passer til dine søgekriterier.\n");
            searchAfterMemberAgain();
            memberList.setSelectedMember(null);
        }
        return memberList.findMember(userInputString);
    }

    private void searchAfterMemberAgain() {
        findMember(ui.findSpecificMemberMenu());
    }

    //Sub-method to get back to findMember search again
    private String userInputString() {
        return ui.userInputString();
    }

    private int isJunior() {
        int isJunior = 0;
        boolean badChoice = true;
        while (badChoice) {
            ui.isJunior();
            isJunior = ui.userInputInt();
            if (isJunior < 0 || isJunior > 2) {
                ui.statusMessage(Status.INVALID_CHOICE);
            } else {
                badChoice = false;
            }
        }
        return isJunior;
    }

    private ArrayList<CompetitiveMember> getJuniorOrSenior(int isJunior) {
        ArrayList<CompetitiveMember> compMem = getCompMembers();
        ArrayList<CompetitiveMember> compMemToRemove = new ArrayList<>();
        if (isJunior == 1) {
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
        return compMem;
    }

    private void topFive() {
        int isJunior = isJunior();
        if (isJunior != 0) {
            ArrayList<CompetitiveMember> compMem = getJuniorOrSenior(isJunior);
            Disciplines discipline = ui.pickDiscipline();
            if (discipline != null) {
                boolean goAgain = true;
                while (goAgain) {
                    ui.topFiveMenu();
                    String IsTraining = userInputString();
                    switch (IsTraining) {
                        case "1" -> getTopFiveComp(compMem, discipline);
                        case "2" -> getTopFiveTraining(compMem, discipline);
                        case "0" -> goAgain = false;
                        default -> ui.statusMessage(Status.INVALID_CHOICE);
                    }
                }
            }
        }
    }

    private void getTopFiveComp(ArrayList<CompetitiveMember> compMem, Disciplines discipline) {
        ArrayList<CompetitonResult> results = new ArrayList<>();
        ArrayList<CompetitonResult> resultsInDiscipline = new ArrayList<>();

        for (Competition comps : listOfComps) {
            results.addAll(comps.getResults());
        }
        for (CompetitonResult compRes : results) {
            if (compRes.getDiscipline() == discipline) {
                String memberIDs = compRes.getMemberID();
                for (CompetitiveMember compMember : compMem) {
                    if (memberIDs.equals(compMember.getMembershipNumber())) {
                        resultsInDiscipline.add(compRes);
                    }
                }
            }
        }
        resultsInDiscipline.sort(Comparator.comparing(CompetitonResult::getTime));
        if (resultsInDiscipline.size() < 5) {
            for (CompetitonResult competitonResult : resultsInDiscipline) {
                String memberID = competitonResult.getMemberID();
                double time = competitonResult.getTime();
                ui.printMessage("MedlemsID: " + memberID + "  -  Tid: " + time + "\n");
            }
        } else {
            for (int i = 0; i < 5; i++) {
                String memberID = resultsInDiscipline.get(i).getMemberID();
                double time = resultsInDiscipline.get(i).getTime();
                ui.printMessage("MedlemsID: " + memberID + "  -  Tid: " + time + "\n");
            }
        }
    }

    private void getTopFiveTraining(ArrayList<CompetitiveMember> compMem, Disciplines discipline) {
        HashMap<Double, String> allTrainingResults = new HashMap<>();
        for (CompetitiveMember actMem : compMem) {

            for (BestTrainingResult trainingResult : actMem.getBestTrainingResults()) {
                if (trainingResult.getDiscipline() == discipline) {
                    allTrainingResults.put(trainingResult.getTime(), actMem.getMembershipNumber());
                }
            }
        }
        //Treemap til at sortere hashmappet
        TreeMap<Double, String> sortedTrainingResults = new TreeMap<>(allTrainingResults);
        sortedTrainingResults.putAll(allTrainingResults);
        if (sortedTrainingResults.size() < 5) {
            for (int i = 0; i <= sortedTrainingResults.size() + i; i++) {
                String memberID = sortedTrainingResults.firstEntry().getValue();
                double time = sortedTrainingResults.firstKey();
                ui.printMessage("MedlemsID: " + memberID + "  -  Tid: " + time + "\n");
                sortedTrainingResults.remove(sortedTrainingResults.firstKey());
            }
        } else {
            for (int i = 0; i < 5; i++) {
                String memberID = sortedTrainingResults.firstEntry().getValue();
                double time = sortedTrainingResults.firstKey();
                ui.printMessage("MedlemsID: " + memberID + "  -  Tid: " + time + "\n");
                sortedTrainingResults.remove(sortedTrainingResults.firstKey());
            }
        }
    }

    private void competitionMenu() {
        boolean keepGoing = true;
        while (keepGoing) {
            ui.competitionMenu();
            String choice = userInputString();
            switch (choice) {
                case "1" -> createCompetition();
                case "2" -> registerResult();
                case "0" -> keepGoing = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

    private void createCompetition() {
        ui.printMessage("Hvad hedder konkurrencen?\n");
        String compName = userInputString();
        Competition comp = new Competition(compName);
        ui.printMessage("Konkurrence med navn " + compName + " er oprettet\n");
        listOfComps.add(comp);
    }

    private void registerResult() {
        int memberChoice = 0;
        ui.printMessage("Vælg en konkurrence \n");
        int compChoice = getCompChoice();

        if (compChoice != -1) {
            ui.printMessage("Du skal vælge et medlem \n");
            ui.searchOrMemberList();
            String searchOrList = userInputString();
            ArrayList<Member> chosenMember = new ArrayList<>();

            switch (searchOrList) {
                case "1" -> chosenMember.add(getMemberFromSearch());
                case "2" -> {
                    ui.printMessage("Vælg medlem. Tryk 0 for at afbryde\n");
                    ArrayList<CompetitiveMember> compMemberToAdd = chooseCompMemberFromList(getCompMembers());
                    if (compMemberToAdd == null) {
                        memberChoice = -1;
                    } else {
                        chosenMember.addAll(compMemberToAdd);
                    }
                }
                case "0" -> memberChoice = -1;
            }
            if (memberChoice != -1) {

                Disciplines discipline = ui.pickDiscipline();

                ui.printMessage("Hvilken placering fik deltageren\n");
                int placement = ui.userInputInt();
                ui.printMessage("Hvilken tid fik deltageren\n");

                double time = Double.parseDouble(userInputString());

                CompetitonResult result = new CompetitonResult(chosenMember.get(0).getMembershipNumber(), time, placement, discipline);
                listOfComps.get(compChoice).addResult(result);
                ui.printMessage("Et resultat er blevet registeret med info \nID: " + result.getMemberID() +
                        "\nDisiplin: " + result.getDiscipline() +
                        "\nTid: " + result.getTime() +
                        "\nPlacering: " + result.getRank() + "\n");
            }
        }
    }

    private Member getMemberFromSearch() { //TODO Der er noget galt her
        Member[] memberSearchResult = findMember(ui.findSpecificMemberMenu());
        return memberSearchResult[0];
    }

    private int getCompChoice() {
        boolean goAgain = true;
        int compChoice = 0;
        while (goAgain) {
            int count = 1;
            for (Competition comps : listOfComps) {
                ui.printMessage(count + ") - " + comps.getCompetitionName() + "\n");
                count++;
            }
            ui.printMessage("0) - Tilbage\n");
            compChoice = ui.userInputInt();
            compChoice -= 1;
            if (compChoice < listOfComps.size()) {
                goAgain = false;
            } else {
                ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
        return compChoice;
    }

    private ArrayList<CompetitiveMember> getCompMembers() {
        ArrayList<CompetitiveMember> compMem = new ArrayList<>();
        for (Member mem : memberList.getMemberList()) {
            if (mem instanceof CompetitiveMember) {
                compMem.add((CompetitiveMember) mem);
            }
        }
        return compMem;
    }

    private ArrayList<Trainer> getTrainers() {
        ArrayList<Trainer> trainers = new ArrayList<>();
        for (Member mem : memberList.getMemberList()) {
            if (mem instanceof Trainer) {
                trainers.add((Trainer) mem);
            }
        }
        return trainers;
    }

    private void teamMenu() {
        boolean goAgain = true;
        while (goAgain) {
            ui.getTeamMenu();
            String choice = ui.userInputString();
            switch (choice) {
                case "1" -> createTeam();
                case "2" -> editTeam();
                case "0" -> goAgain = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

    private void createTeam() {
        ui.printMessage("Giv holdet et navn. Tryk 0 for at afbryde\n");
        String teamName = ui.userInputString();
        if (!teamName.equals("0")) {
            ui.printMessage("Vælg en træner. Tryk 0 for at afbryde\n");
            ArrayList<Trainer> chosenTrainerToCheck = chooseTrainerFromList(getTrainers());
            if (chosenTrainerToCheck != null) {
                Trainer chosenTrainer = chosenTrainerToCheck.get(0);
                ui.printMessage("Vælg medlem der skal være i teamet. Tryk 0 for at afbryde\n ");
                ArrayList<String> teamMemberIds = selectMembersToTeam();
                Disciplines discipline = ui.pickDiscipline();
                if (discipline != null) {
                    Team newTeam = new Team(teamMemberIds, teamName, discipline);
                    chosenTrainer.addTeam(newTeam);
                    ui.printMessage("Du har oprettet et hold med \n" +
                            "Navn: " + teamName + "\n" +
                            "Træner: " + chosenTrainer.getName() + "\n" +
                            "Disiplin: " + discipline.toString() + "\n" +
                            "Medlemmer: \n");
                    for (String memberID : teamMemberIds) {
                        ui.printMessage(memberID + "\n");
                    }
                    ui.printMessage("\n\n");
                }
            }
        }
    }

    private void editTeam() {
        Team teamToEdit = chooseTeam();
        boolean goAgain = true;
        while (goAgain) {
            ui.editTeam();
            String input = userInputString();
            switch (input) {
                case "1" -> editTeamName(teamToEdit);
                case "2" -> editTeamMembers(teamToEdit);
                case "3" -> editTrainer(teamToEdit);
                case "4" -> editDiscipline(teamToEdit);
                case "5" -> showTeamDetails(teamToEdit);
                case "0" -> goAgain = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
    }

    private void showTeamDetails(Team team) {
        String teamDetails = "";
        teamDetails = "Hold Navn: " + team.getTeamName() + "\n";
        teamDetails = teamDetails + "Hold medlemmer: \n";
        for (String member : team.getTeamMembers()) {
            teamDetails += member + "\n";
        }
        teamDetails += "Disiplin: " + team.getDiscipline();
        ui.printMessage(teamDetails);
    }

    private void editDiscipline(Team teamToEdit) {
        ui.printMessage("Den nuværende disiplin på holdet er " + teamToEdit.getDiscipline() + "\n");
        Disciplines newDiscipline = ui.pickDiscipline();
        if (newDiscipline != null) {
            teamToEdit.setDiscipline(newDiscipline);
            ui.printMessage("Holdets displin er nu " + newDiscipline + "\n");
        }
    }

    private void editTrainer(Team teamToEdit) {
        ArrayList<Trainer> allTrainers = getTrainers();
        Trainer currentTrainer = new Trainer();
        for (Trainer trainer : allTrainers) {
            for (Team team : trainer.getAllTeams()) {
                if (teamToEdit.getTeamName().equals(team.getTeamName())) {
                    currentTrainer = trainer;
                }
            }
        }
        ui.printMessage("Den nuværende træner er " + currentTrainer.getName() + "\nHvem skal være den nye træner?\n");
        ArrayList<Trainer> newTrainer = chooseTrainerFromList(allTrainers);
        newTrainer.get(0).addTeam(teamToEdit);
        currentTrainer.removeTeam(teamToEdit);
    }

    private void editTeamMembers(Team teamToEdit) {
        ui.printMessage("Vil du\n1 - Tilføje medlemmer\n2 - Fjerne medlem\n");
        String choice = userInputString();
        switch (choice) {
            case "1" -> addMemberToTeam(teamToEdit);
            case "2" -> removeMemberFromTeam(teamToEdit);
            default -> ui.statusMessage(Status.INVALID_CHOICE);
        }
    }

    private void removeMemberFromTeam(Team teamToEdit) {
        ArrayList<String> allTeamMembers = teamToEdit.getTeamMembers();
        ui.printMessage("Hvilket medlem vil du fjerne. Tryk 0 for at afbryde\n");
        boolean moreMembersToRemove = true;
        int memberToRemove = 0;
        while (moreMembersToRemove) {
            int count = 1;
            for (String teamMember : allTeamMembers) {
                ui.printMessage(count + ") - " + teamMember + "\n");
                count++;
            }
            memberToRemove = ui.userInputInt() - 1;
            if (allTeamMembers.size() - 1 != 0) {
                ui.printMessage("Vil du fjerne flere medlemmer (j/n)");
                String removeMore = userInputString();
                switch (removeMore) {
                    case "n" -> moreMembersToRemove = false;
                    case "j" -> allTeamMembers.remove(memberToRemove);
                    default -> ui.printMessage("Du kan kun vælge j eller n\n");
                }
            } else {
                ui.printMessage("Holdet er nu tomt\n");
                moreMembersToRemove = false;
            }
        }
        allTeamMembers.remove(memberToRemove);
    }

    private ArrayList<String> selectMembersToTeam() {
        ArrayList<CompetitiveMember> allCompMembers = getCompMembers();
        ArrayList<CompetitiveMember> chosenMembers = new ArrayList<>();
        ArrayList<CompetitiveMember> tempMemberToCheck = new ArrayList<>();
        ArrayList<String> chosenMembersID = new ArrayList<>();
        boolean moreMembers = true;
        while (moreMembers) {
            tempMemberToCheck = chooseCompMemberFromList(allCompMembers);
            if (tempMemberToCheck == null) {
                break;
            } else {
                chosenMembers.addAll(tempMemberToCheck);
                ui.printMessage("Vil du tilføje flere medlemmer (j/n)");
                String choice = userInputString();
                switch (choice) {
                    case "j" -> allCompMembers.removeAll(chosenMembers);
                    case "n" -> moreMembers = false;
                    default -> ui.printMessage("Du kan kun vælge j eller n");
                }
            }
        }
        if (tempMemberToCheck != null) {
            for (CompetitiveMember compMem : chosenMembers) {
                chosenMembersID.add(compMem.getMembershipNumber());
            }
        }
        return chosenMembersID;
    }

    private void addMemberToTeam(Team teamToEdit) {
        ui.printMessage("Vælg medlem der skal tilføjes til teamet. Tryk 0 for at afbryde\n ");
        ArrayList<String> teamMemberIds = selectMembersToTeam();
        teamToEdit.addMembers(teamMemberIds);
    }

    private Team chooseTeam() {
        ArrayList<Trainer> allTrainers = getTrainers();
        ArrayList<Team> allTeams = new ArrayList<>();
        boolean goAgain = true;
        Team chosenTeam = new Team();
        for (Trainer trainer : allTrainers) {
            allTeams.addAll(trainer.getAllTeams());
        }
        int count = 1;
        ui.printMessage("Vælg et hold. Tryk 0 for at afbryde\n");
        while (goAgain) {
            for (Team team : allTeams) {
                ui.printMessage(count + ") - " + team.getTeamName() + "\n");
                count++;
            }
            int teamChoice = ui.userInputInt();
            teamChoice -= 1;
            if (teamChoice < allTeams.size() && teamChoice > -1) {
                goAgain = false;
                chosenTeam = allTeams.get(teamChoice);
            } else if (teamChoice == -1) {
                chosenTeam = null;
                goAgain = false;
            } else {
                ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
        return chosenTeam;
    }

    private void editTeamName(Team teamToEdit) {
        ui.printMessage("Hvad vil du skifte holdnavnet til? Tryk 0 for at afbryde\n");
        String newName = userInputString();
        if (!newName.equals("0")) {
            ui.printMessage("Holdnavnet er skiftet fra " + teamToEdit.getTeamName() + " til " + newName + "\n\n");
            teamToEdit.setTeamName(newName);
        }
    }

    private ArrayList<Trainer> chooseTrainerFromList(ArrayList<Trainer> listSelectFrom) {
        boolean goAgain = true;
        ArrayList<Trainer> chosenMember = new ArrayList<>();
        while (goAgain) {
            int count = 1;
            for (Member member : listSelectFrom) {
                ui.printMessage(count + ") - " + member.getName() + "\n");
                count++;
            }
            int trainerChoice = ui.userInputInt() - 1;

            if (trainerChoice < listSelectFrom.size() && trainerChoice > -1) {
                goAgain = false;
                chosenMember.add(listSelectFrom.get(trainerChoice));
            } else if (trainerChoice == -1) {
                chosenMember = null;
                goAgain = false;
            } else {
                ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
        return chosenMember;

    }

    private ArrayList<CompetitiveMember> chooseCompMemberFromList(ArrayList<CompetitiveMember> listSelectFrom) {
        boolean goAgain = true;
        ArrayList<CompetitiveMember> chosenMember = new ArrayList<>();
        while (goAgain) {
            int count = 1;
            for (Member mem : listSelectFrom) {
                ui.printMessage(count + ") - " + mem.getName() + "\n");
                count++;
            }
            int competitiveMemberChoice = ui.userInputInt() - 1;

            if (competitiveMemberChoice < listSelectFrom.size() && competitiveMemberChoice > -1) {
                goAgain = false;
                chosenMember.add(listSelectFrom.get(competitiveMemberChoice));
            } else if (competitiveMemberChoice == -1) {
                chosenMember = null;
                goAgain = false;
            } else {
                ui.statusMessage(Status.INVALID_CHOICE);
            }
        }
        return chosenMember;

    }

    private void setDisciplineOfSwimmer() {
        ui.printMessage("Vælg en svømmer. Tryk 0 for at afbryde\n");
        ArrayList<CompetitiveMember> allCompMembers = getCompMembers();
        ArrayList<CompetitiveMember> memberToEdit = chooseCompMemberFromList(allCompMembers);
        if (memberToEdit != null) {
            Disciplines disciplineToSet = ui.pickDiscipline();
            if (disciplineToSet != null) {
                memberToEdit.get(0).addDiscipline(disciplineToSet);
            }
        }
    }

    private void trainingResultMenu() {


        boolean goAgain = true;
        while (goAgain) {
            ui.trainingResultsMenu();
            String choice = userInputString();
            switch (choice) {
                case "1" -> createTrainingResult();
                case "2" -> seeTrainingResults();
                case "0" -> goAgain = false;
                default -> ui.statusMessage(Status.INVALID_CHOICE);
            }
        }

    }

    private void seeTrainingResults() {
        ui.printMessage("Vælg en svømmer. Tryk 0 for at afbryde\n");
        ArrayList<CompetitiveMember> allCompMembers = getCompMembers();
        ArrayList<CompetitiveMember> chosenMember = chooseCompMemberFromList(allCompMembers);
        CompetitiveMember member = chosenMember.get(0);
        ArrayList<BestTrainingResult> results = new ArrayList<>();
        if (chosenMember != null) {
            results = chosenMember.get(0).getBestTrainingResults();
            String resultsInfo = "Træningsresultater for " + member.getName() + "\n";
            for (BestTrainingResult result : results) {
                resultsInfo += "Resultat i disiplin: " + result.getDiscipline() + "\nTid: " + result.getTime() + "\n" + "Dato for resultat: " + result.getDateBestResult() + "\n";
            }
            ui.printMessage(resultsInfo);
        }
    }

    private void createTrainingResult() {
        ui.printMessage("Vælg en svømmer. Tryk 0 for at afbryde\n");
        ArrayList<CompetitiveMember> allCompMembers = getCompMembers();
        ArrayList<CompetitiveMember> chosenMember = chooseCompMemberFromList(allCompMembers);
        if (chosenMember != null) {

            int day;
            int month;
            int year;
            Disciplines discipline = ui.pickDiscipline();
            if (discipline != null) {
                ui.printMessage("Hvilken tid fik medlemmet. Tryk 0 for at afbryde\n");
                double time = Double.parseDouble(userInputString());
                if (time != 0) {
                    ui.printMessage("Dato hvor resultat er opnået. Tryk 0 for at afbryde\n");
                    ui.printMessage("Dag: ");
                    day = ui.userInputInt();
                    if (day != 0) {
                        ui.printMessage("Måned: ");
                        month = ui.userInputInt();
                        if (month != 0) {
                            ui.printMessage("År: ");
                            year = ui.userInputInt();
                            if (year != 0) {
                                BestTrainingResult result = new BestTrainingResult(year, month, day, time, discipline);
                                chosenMember.get(0).addBestTrainingResult(result);
                                ui.printMessage("Et træningsresultat er registreret med info \nNavn: " + chosenMember.get(0).getName() +
                                        "\nDisiplin: " + result.getDiscipline() +
                                        "\nTid: " + result.getTime() + "\n");
                            }
                        }
                    }
                }

            }
        }
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
        CompetitiveMember cmember = new CompetitiveMember("Jesper Binsmark", "94012348", "Jespers@mail.com", "Jesperdutrordetløgngade 12", 1, 3, 1912);
        CompetitiveMember cmember2 = new CompetitiveMember("Sanne Salamonsen", "99882277", "Sannes@mail.com", "langgade", 12, 12, 1936);
        CompetitiveMember cmember3 = new CompetitiveMember("Casper Kristensen", "82391200", "Caspers@mail.com", "Casskadevej 99", 2, 1, 1999);
        CompetitiveMember cmember4 = new CompetitiveMember("Dorthe dierslev", "12483799", "Dorthes@mail.com", "Dodalidavej 192", 22, 5, 1990);
        CompetitiveMember cmember5 = new CompetitiveMember("Julemand Jul", "24242424", "Julemandens@mail.com", "Nordpolen 1", 24, 12, 1200);
        memberList.addMember(cmember);
        memberList.addMember(cmember2);
        memberList.addMember(cmember3);
        memberList.addMember(cmember4);
        memberList.addMember(cmember5);
        Trainer tmember = new Trainer("Jesus Javier", "13425334", "Javiersmail@mail.com", "Jerusalems vej 2", 11, 12, 1932);
        Trainer tmember2 = new Trainer("Færdinand Franz", "02394123", "Franzmail@mail.com", "Ungarns vej 14", 2, 2, 1966);
        Trainer tmember3 = new Trainer("Elizabeth QueenToBe", "09898945", "Elizabeths@mail.com", "Kongehuset 12", 1, 1, 1909);
        Trainer tmember4 = new Trainer("Sally Soller", "12854930", "Sallys@mail.com", "Solens vej 99", 13, 12, 1920);
        Trainer tmember5 = new Trainer("Amira Sørensen", "8475901", "Amiras@mail.com", "Nørrebrogade 112", 2, 4, 2001);
        memberList.addMember(tmember);
        memberList.addMember(tmember2);
        memberList.addMember(tmember3);
        memberList.addMember(tmember4);
        memberList.addMember(tmember5);
        ArrayList<String> ids = new ArrayList<>();
        ids.add("cttr0928");
        ids.add("cskr3120");
        Team newTeam = new Team(ids, "Hold1", Disciplines.BACK_CRAWL);
        Team newTeam2 = new Team(ids, "Hold2", Disciplines.BACK_CRAWL);
        Team newTeam3 = new Team(ids, "Hold3", Disciplines.BACK_CRAWL);
        Team newTeam4 = new Team(ids, "Hold4", Disciplines.BACK_CRAWL);
        tmember.addTeam(newTeam);
        tmember2.addTeam(newTeam2);
        tmember3.addTeam(newTeam3);
        tmember5.addTeam(newTeam4);


        ArrayList<CompetitiveMember> compMem = new ArrayList<>();
        for (Member mem : memberList.getMemberList()) {
            if (mem instanceof CompetitiveMember) {
                compMem.add((CompetitiveMember) mem);
            }
        }

        Competition comp = new Competition("Test");
        Competition comp2 = new Competition("Test2");
        listOfComps.add(comp);
        CompetitonResult result = new CompetitonResult("cskr6795", 60.0, 2, Disciplines.BACK_CRAWL);
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 55.0, 2, Disciplines.BACK_CRAWL);
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 50.0, 2, Disciplines.BACK_CRAWL);
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 40.0, 2, Disciplines.BACK_CRAWL);
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 30.0, 2, Disciplines.BACK_CRAWL);
        comp.addResult(result);
        result = new CompetitonResult(compMem.get(0).getMembershipNumber(), 20, 2, Disciplines.BACK_CRAWL);
        comp.addResult(result);
        comp2.addResult(result);

        LocalDate now = LocalDate.now();
        compMem.get(0).addBestTrainingResult(now, 20, Disciplines.BACK_CRAWL);
        compMem.get(0).addBestTrainingResult(now, 20, Disciplines.CRAWL);
        compMem.get(1).addBestTrainingResult(now, 25, Disciplines.BACK_CRAWL);
        compMem.get(2).addBestTrainingResult(now, 30, Disciplines.BACK_CRAWL);
        compMem.get(3).addBestTrainingResult(now, 35, Disciplines.BACK_CRAWL);
        compMem.get(4).addBestTrainingResult(now, 40, Disciplines.BACK_CRAWL);
        //compMem.get(5).addBestTrainingResult(now, 41, Disciplines.BACK_CRAWL);

        users.add(new User("Janhej", "j1234"));
        users.add(new User("lisfis", "l1234"));
        System.out.println(users.get(0));
        System.out.println(users.get(1));

    }


}
