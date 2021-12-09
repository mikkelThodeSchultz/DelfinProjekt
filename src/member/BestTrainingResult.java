//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;
import ui.Disciplines;

import java.time.LocalDate;
import java.time.LocalDateTime;


//@author Mark Stone
public class BestTrainingResult {
    private double time;
    private LocalDate dateBestResult;
    private Disciplines discipline;

    public BestTrainingResult(){}

    public BestTrainingResult(int year, int month, int day, double bestTime, Disciplines disciplin) {
        this.dateBestResult = LocalDate.of(year, month, day);
        this.time = bestTime;
        this.discipline = disciplin;
    }

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
