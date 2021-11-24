package ui;

import domain.Controller;
import member.MemberList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner sc = new Scanner(System.in);

    public String userInputString(){
        return sc.nextLine();
    }

    public void getWelcomeMessage(){
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Velkommen til Delfinen.
                Den største svømmeklub i Nørrum Snede.
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);

    }

    public void getMainMenu(){
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
    }

    public void getMemberMenu(){ //TODO der skal laves en søgemetode til at finde medlemmer - se PETLATKEA på github.
        System.out.println("""
                Medlemmer
                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Find medlem
                2 - Opret medlem
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
    }

    public void getFoundMemberMenu(){
        //TODO redigering i et medlems info bør også afføde ændring i medlemsnummer. Dvs metode kald.
        System.out.println("""
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.

                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Rediger navn
                2 - Rediger adresse
                3 - Rediger telefonnummer
                4 - Rediger fødselsdato
                5 - Slet medlem [NB kan ikke omgøres]
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
    }

    public void getPaymentsMenu (){
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
    }

    public void getCompetitionMenu(){
        System.out.println("""
                Konkurrencer
                
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                1 - Top 5 lister
                2 - Registrer resultat
                3 - Tilknyt disciplin
                0 - Tilbage til hovedmenu
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);
    }

    public void statusMessage(Status status){ //TODO udfyld de her med fornuftige beskeder
        switch (status){
            case OK -> System.out.println("");
            case NO -> System.out.println("");
            case ERROR -> System.out.println("");
            case MEMBER_NOT_FOUND -> System.out.println("Kunne ikke finde det medlem.");
            case SELECT_MEMBER -> System.out.println("Vælg venligst et medlem.");
            case SELECT_DISCIPLINE -> System.out.println("Vælg venligst en disciplin.");
        }
    }

}

