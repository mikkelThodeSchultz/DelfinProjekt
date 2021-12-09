package member;
import ui.Disciplines;

import java.time.LocalDate;

//@author Mark Stone
public class BestTrainingResult {
    private double time;
    private LocalDate dateBestResult;
    private Disciplines discipline;

    // Needed for Json, do not delete
    public BestTrainingResult(){}

    public BestTrainingResult(int year, int month, int day, double bestTime, Disciplines disciplin) {
        this.dateBestResult = LocalDate.of(year, month, day);
        this.time = bestTime;
        this.discipline = disciplin;
    }
    //Til generering af testdata
    public BestTrainingResult(LocalDate date, double time, Disciplines disciplin){
        this.dateBestResult = date;
        this.time = time;
        this.discipline = disciplin;
    }

    public double getTime() {
        return time;
    }

    public Disciplines getDiscipline(){
        return discipline;
    }

    public LocalDate getDateBestResult(){
        return dateBestResult;
    }
}
