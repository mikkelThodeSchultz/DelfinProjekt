package member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ui.Disciplines;
import ui.Role;

//@author Fie

public class CompetitiveMember extends Member {
    private ArrayList<BestTrainingResult> bestTrainingResults = new ArrayList();
    private ArrayList<Disciplines> disciplines = new ArrayList<>();
    private Role role;

    public CompetitiveMember(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
        super(name, phoneNumber, email, homeAddress, day, month, year);
        role = Role.KONKURRENCE;
    }

    //USED FOR JSON. DO NOT DELETE
    public CompetitiveMember() {
    }
    //Used for testdata
    public void addBestTrainingResult(LocalDate timeWhenAccomplished, double time, Disciplines discipline) {
        BestTrainingResult resultToAdd = new BestTrainingResult(timeWhenAccomplished, time, discipline);
        bestTrainingResults.add(resultToAdd);
    }

    public void addBestTrainingResult(BestTrainingResult result) {
        bestTrainingResults.add(result);
    }

    public ArrayList<BestTrainingResult> getBestTrainingResults() {
        return bestTrainingResults;
    }

    public Role getRole() {
        return role;
    }

    public void addDiscipline(Disciplines discipline) {
        disciplines.add(discipline);
    }

    public String toString() {
        String status;
        if (getIsActive()) {
            status = setIsActive();
        } else {
            status = setIsPassive();
        }
        return super.toString() + " – " + role.toString() + " - (" + status + ")";
    }
}
