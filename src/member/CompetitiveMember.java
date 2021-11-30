//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import java.time.LocalDateTime;
import java.util.ArrayList;

import ui.Disciplines;

public class CompetitiveMember extends Member {
    ArrayList<BestTrainingResult> bestTrainingResults = new ArrayList();
    ArrayList<Disciplines> disciplines = new ArrayList<>();

    //TODO lav metode der fanger disciplin dubletter

    public CompetitiveMember(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
        super (name, phoneNumber, email, homeAddress, day, month, year);
    }



    //USED FOR JSON. DO NOT DELETE
    public CompetitiveMember(){

    }
    public void addBestTrainingResult(LocalDateTime timeWhenAccomplished, double time, Disciplines discipline){
        BestTrainingResult resultToAdd = new BestTrainingResult(timeWhenAccomplished, time, discipline.toString());
        bestTrainingResults.add(resultToAdd);
    }

    public ArrayList<BestTrainingResult> getBestTrainingResults(){
        return bestTrainingResults;
    }

}
