package member;

import org.javatuples.Quartet;
import ui.Disciplines;

import java.util.ArrayList;

public class CompetitonResult {
    //Double for time, int for rank, Enum for Discipline
    double time;
    int rank;
    Enum<Disciplines> discipline;
    String memberID;

    public CompetitonResult(){}

    public CompetitonResult(String memberID, double time, int rank, Enum<Disciplines> discipline){
        this.time = time;
        this.rank = rank;
        this.discipline = discipline;
        this.memberID = memberID;

    }
}
