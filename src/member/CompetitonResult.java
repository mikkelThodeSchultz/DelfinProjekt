package member;

import ui.Disciplines;

//@author Mark Stone

public class CompetitonResult {

    private double time;
    private int rank;
    private Disciplines discipline;
    private String memberID;

    public CompetitonResult() {}

    public CompetitonResult(String memberID, double time, int rank, Disciplines discipline) {
        this.time = time;
        this.rank = rank;
        this.discipline = discipline;
        this.memberID = memberID;
    }

    public double getTime() {
        return time;
    }

    public int getRank() {
        return rank;
    }

    public Disciplines getDiscipline() {
        return discipline;
    }

    public String getMemberID() {
        return memberID;
    }


}
