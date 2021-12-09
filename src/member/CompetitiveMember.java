//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import java.time.LocalDateTime;
import java.util.ArrayList;

import ui.Disciplines;
import ui.Role;

public class CompetitiveMember extends Member {
    private ArrayList<BestTrainingResult> bestTrainingResults = new ArrayList();
    private ArrayList<Disciplines> disciplines = new ArrayList<>();
    private Role role;

    public CompetitiveMember(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
        super (name, phoneNumber, email, homeAddress, day, month, year);
        role = Role.KONKURRENCE;
    }

    //USED FOR JSON. DO NOT DELETE
    public CompetitiveMember(){}

    public void addBestTrainingResult(LocalDateTime timeWhenAccomplished, double time, Disciplines discipline){
        BestTrainingResult resultToAdd = new BestTrainingResult(timeWhenAccomplished, time, discipline);
        bestTrainingResults.add(resultToAdd);
    }

    public ArrayList<BestTrainingResult> getBestTrainingResults(){
        return bestTrainingResults;
    }

    public Role getRole() {
        return role;
    }

    public void addDiscipline(Disciplines discipline){
        disciplines.add(discipline);
    }

    public String toString(){
        String status = null;
        if (getIsActive()){
            status = setIsActive();
        } else {
            status = setIsPassive();
        }
        return super.toString() + " – "  + role.toString() + " - (" + status + ")";
    }
}
