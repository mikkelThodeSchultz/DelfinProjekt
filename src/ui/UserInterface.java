package ui;

import domain.Controller;
import member.Member;
import member.MemberList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner sc = new Scanner(System.in);

    public String userInputString(){
        return sc.nextLine();
    }

    public int userInputInt(){
        return sc.nextInt();
    }

    public void printMessage(String message){
        System.out.print(message);
    }

    public void getWelcomeMessage(){
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Velkommen til Delfinen.
                Den største svømmeklub i Nørrum Snede.
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);

    }

    public String getMainMenu(){
        System.out.println("""
                Hovedmenu
                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Medlemmer
                2 - Kontingenter
                3 - Konkurrencer
                
                0 - Luk programmet
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);

        return sc.nextLine();
    }

    public String getMemberMenu(){ //TODO der skal laves en søgemetode til at finde medlemmer - se PETLATKEA på github.

        System.out.println("""
                Medlemsmenu
                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Vis medlemmer
                2 - Find medlem
                3 - Opret medlem
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
        return sc.nextLine();
    }

    public void showMemberList(String listOfMembers){
        System.out.println(listOfMembers);
    }

    public String findSpecificMemberMenu(){
        System.out.println("""
                Skriv en del af eller hele navnet på det medlem, du ønsker at finde: """);
        return sc.nextLine();
    }

    public String getFoundMemberMenu(){
        //TODO redigering i et medlems info bør også afføde ændring i medlemsnummer. Dvs metode kald.

        System.out.println("""
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.

                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Rediger navn
                2 - Rediger adresse
                3 - Rediger e-maildresse
                4 - Rediger telefonnummer
                5 - Rediger fødselsdato
                6 - Rediger medlemsskabsstatus
                7 - Rediger svømmeniveau
                8 - Slet medlem [NB kan ikke omgøres]
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
        return sc.nextLine();
    }

    public String getPaymentsMenu (){
        System.out.println("""
                Kontingenter
                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Forventet indtjening
                2 - Modtag betaling
                3 - Vis restancer
                4 - Opkræv kontingenter
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
                """);
        return sc.nextLine();
    }

    public String getCompetitionMenu(){
        System.out.println("""
                Konkurrencer
                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Top 5 lister
                2 - Registrer resultat(er)
                3 - Tilknyt disciplin(er)
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
        return sc.nextLine();
    }

    public Status statusMessage(Status status){ //TODO udfyld de her med fornuftige beskeder
        switch (status){
            case OK -> System.out.println("");
            case NO -> System.out.println("");
            case ERROR -> System.out.println("Der er opstået et problem.");
            case MEMBER_NOT_FOUND -> System.out.println("Kunne ikke finde det medlem du søgte.");
            case SELECT_MEMBER -> System.out.println("Vælg venligst et medlem.");
            case SELECT_DISCIPLINE -> System.out.println("Vælg venligst en disciplin.");
            case ACTIVE -> System.out.println("");
            case INVALID_CHOICE -> System.out.println("Ugyldigt input.\nPrøv venligst med et der er gyldigt.");
        }
        return status;
    }

    //Standardmember – pr. default er man excersier
    public String[] createNewMember() { //TODO bør ligge i memberlist

        printMessage("Indtast information om det nye medlem: ");
        printMessage("\nNavn: ");
        String name = userInputString();
        printMessage("Telefonnummer: ");
        String phoneNumber = userInputString();
        printMessage("E-mail: ");
        String email = userInputString();
        printMessage("Adresse: ");
        String homeAddress = userInputString();
        printMessage("Indtast fødselsdagsoplysninger: ");
        printMessage("Dag: ");
        String day = userInputString();
        printMessage("Måned: ");
        String month = userInputString();
        printMessage("År: ");
        String year = userInputString();

        String[] memberInfo = {name, phoneNumber, email, homeAddress, day, month, year};
        return memberInfo;
    }

    public void changeMessage(String oldInfo, String newInfo){
        System.out.println("Du har nu ændret " + oldInfo + " til " + newInfo+".");
    }

    public void topFiveMenu(){
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

    public Disciplines topFiveDisipline(){
        System.out.println("""
                Vælg disipline
                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Crawl
                2 - Rygcrawl
                3 - Butterfly
                4 - Brystsvømning
                0 - Tilbage
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
        String disipline = sc.nextLine();
        Disciplines chosenDis;

        if(disipline.equals("1")){
            chosenDis = Disciplines.CRAWL;
        }
        else if (disipline.equals("2")){
            chosenDis = Disciplines.BACK_CRAWL;
        }
        else if (disipline.equals("3")){
            chosenDis = Disciplines.BUTTERFLY;
        }
        else if (disipline.equals("4")){
            chosenDis = Disciplines.BREASTSTROKE;
        }
        else {
            chosenDis = null;
        }

        return chosenDis;

    }
    public void isJunior(){
        System.out.println("""
                Vælg junior eller senior
                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Junior
                2 - Senior
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
    }

}

