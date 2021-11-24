package ui;

import domain.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner sc = new Scanner(System.in);


    public String userInputString(){
        return sc.nextLine();
    }

    public String printMemberList(String string){
        System.out.println(string);
        return userInputString();
    }

    public void printMessage (String string){
        System.out.println(string);
    }

    public void getWelcomeMessage(){
        System.out.println("""
                Velkommen til Delfinen.
                Den største svømmeklub i Nørrum Snede
                """);

    }

    public void getMainMenu(){
        System.out.println("""
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                1 - Medlemmer
                2 - Kontingenter
                3 - Konkurrencer
                0 - Luk programmet
            
                """);
    }

    public void getMemberMenu(){
        System.out.println("""
                Tryk på den tast der svarer til det menupunkt du ønsker at vælge.
                
                1 - Find medlem
                2 - Opret medlem
                0 - Tilbage til hovedmenu
                
                """);
    }

    public void getFindMemberMenu(){
        //TODO redigering i et medlems info bør også afføde ændring i medlemsnummer
        System.out.println("""
                1 - Rediger navn
                2 - Rediger adresse
                3 - Rediger telefonnummer
                4 - Rediger fødselsdato
                
                """);
    }
}

