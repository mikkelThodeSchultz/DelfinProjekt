package ui;

import java.util.Scanner;

public class UserInterface {
    private Scanner sc = new Scanner(System.in);

    public String userInputString() {
        return sc.nextLine();
    }

    public int userInputInt() {
        boolean badChoice = true;
        int intputAsInt = 0;
        while (badChoice) {
            String input = sc.nextLine();

            try {
                intputAsInt = Integer.parseInt(input);
                badChoice = false;
            } catch (NumberFormatException e) {
                System.out.println("Indtast et tal");
            }
        }
        return intputAsInt;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void getWelcomeMessage() {
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Velkommen til Delfinen.
                Den største svømmeklub i Nørrum Snede.
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);

    }

    public String getMainMenu() {
        System.out.println("""
                                
                                               Hovedmenu
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Medlemmer
                2 - Kontingenter
                3 - Konkurrencer og holdmenu
                                
                7 - GenerateTestData
                8 - Gem             
                9 - Clear Json
                0 - Gem og Luk programmet
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);

        return sc.nextLine();
    }

    public String getMemberMenu() {

        System.out.println("""
                                
                                               Medlemsmenu
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Vis medlemmer
                2 - Rediger medlem
                3 - Opret medlem
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
        return sc.nextLine();
    }

    public void showMemberList(String listOfMembers) {
        System.out.println(listOfMembers);
    }

    public String findSpecificMemberMenu() {
        System.out.println("""
                Skriv en del af eller hele navnet på det medlem, du ønsker at finde: """);
        return sc.nextLine();
    }

    public String getFoundMemberMenu() {
        System.out.println("""
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.

                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Søg efter medlem
                2 - Rediger navn
                3 - Rediger adresse
                4 - Rediger e-maildresse
                5 - Rediger telefonnummer
                6 - Rediger fødselsdato
                7 - Rediger medlemsskabsstatus
                8 - Rediger svømmeniveau
                9 - Slet medlem [NB kan ikke omgøres]
                0 - Tilbage til medlemsmenuen
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
        return sc.nextLine();
    }

    public String getCreateMemberMenu() {
        System.out.println("""
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.

                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Opret standardmedlem
                2 - Opret konkurrencemedlem
                3 - Opret træner
                0 - Tilbage til medlemsmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
        return sc.nextLine();
    }

    public String getPaymentsMenu() {
        System.out.println("""
                                
                                               Kontingentmenu
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Forventet indtjening
                2 - Modtag betaling
                3 - Vis restancer
                4 - Nulstil kontingenter
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
                """);
        return sc.nextLine();
    }

    public String TrainerMenu() {
        System.out.println("""
                                
                                              TrænerMenu
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Top 5 lister
                2 - Registrer resultat(er) eller opret konkurrence
                3 - Holdmenuen
                4 - Tilknyt disiplin til svømmer
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
        return sc.nextLine();
    }

    public Status statusMessage(Status status) {
        switch (status) {
            case ANNULLERET -> System.out.println("Handling annulleret.");
            case ERROR -> System.out.println("Der er opstået et problem.");
            case MEMBER_NOT_FOUND -> System.out.println("Kunne ikke finde det medlem du søgte.");
            case SELECT_MEMBER -> System.out.println("Vælg venligst et medlem.");
            case SELECT_DISCIPLINE -> System.out.println("Vælg venligst en disciplin.");
            case INVALID_CHOICE -> System.out.println("Ugyldigt input.\nPrøv venligst med et der er gyldigt.");
        }
        return status;
    }

    //Standardmember – pr. default er man excersier
    public String[] createNewMember() {
        String phoneNumber = "";
        String email = "";
        String homeAddress = "";
        String day = "";
        String month = "";
        String year = "";
        printMessage("Indtast information om det nye medlem. Du kan altid trykke 0 for at afbryde");
        printMessage("\nNavn: ");

        String name = userInputString();
        if (!name.equals("0")) {
            printMessage("Telefonnummer: ");
            phoneNumber = userInputString();
            if (!phoneNumber.equals("0")) {
                printMessage("E-mail: ");
                email = userInputString();
                if (!email.equals("0")) {
                    printMessage("Adresse: ");
                    homeAddress = userInputString();
                    if (!homeAddress.equals("0")) {
                        printMessage("Indtast fødselsdagsoplysninger: \n");
                        printMessage("Dag: ");
                        day = userInputString();
                        if (!day.equals("0")) {
                            printMessage("Måned: ");
                            month = userInputString();
                            if (!month.equals("0")) {
                                printMessage("År: ");
                                year = userInputString();
                            }
                        }
                    }
                }
            }
        }
        String[] memberInfo = {name, phoneNumber, email, homeAddress, day, month, year};
        return memberInfo;
    }

    public void changeMessage(String oldInfo, String newInfo) {
        System.out.println("Du har nu ændret " + oldInfo + " til " + newInfo + ".");
    }

    public void topFiveMenu() {
        System.out.println("""
                                               Top 5 menu
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Top 5 i konkurrencer
                2 - Top 5 i træning
                0 - Tilbage
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
    }

    public Disciplines pickDiscipline() {
        System.out.println("""
                                             Vælg disciplin
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Crawl
                2 - Rygcrawl
                3 - Butterfly
                4 - Brystsvømning
                0 - Tilbage
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
        int discipline = userInputInt();
        Disciplines chosenDis;

        if (discipline == 1) {
            chosenDis = Disciplines.CRAWL;
        } else if (discipline == 2) {
            chosenDis = Disciplines.BACK_CRAWL;
        } else if (discipline == 3) {
            chosenDis = Disciplines.BUTTERFLY;
        } else if (discipline == 4) {
            chosenDis = Disciplines.BREASTSTROKE;
        } else {
            chosenDis = null;
        }

        return chosenDis;

    }

    public void isJunior() {
        System.out.println("""
                                           Vælg junior eller senior
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Junior
                2 - Senior
                
                0 - Tilbage
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
    }

    public void competitionMenu() {
        System.out.println("""
                Vil du oprette en konkurrence eller registere et resultat
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Opret konkurrence
                2 - Registre resultat
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);

    }

    public void searchOrMemberList() {
        System.out.println("""
                Vil du søge efter et medlem eller se en liste over alle medlemmer
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Søg efter medlem
                2 - Se liste Af medlemmer
                0 - Tilbage
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
    }

    public void getTeamMenu() {
        System.out.println("""
                                                 Holdmenu
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Oprette et hold
                2 - Redigere et hold eller få info om hold
                0 - Tilbage
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);

    }

    public void editTeam() {
        System.out.println("""
                                                 Rediger hold
                                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Rediger holdnavn
                2 - Rediger holdmedlemmer
                3 - Rediger træner
                4 - Rediger disiplin
                5 - Vis info om hold
                0 - Tilbage
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
    }

}

