//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;
import ui.Disciplines;
import java.time.LocalDateTime;


//@author Mark Stone
public class BestTrainingResult {
    private double time;
    private LocalDateTime dateBestResult;
    private Disciplines discipline;

    public BestTrainingResult(){}

    public BestTrainingResult(LocalDateTime dateBestResult, double bestTime, Disciplines disciplin) {
        this.dateBestResult = dateBestResult;
        this.time = bestTime;
        this.discipline = disciplin;
    }

    public double getTime() {
        return time;
    }

    public Disciplines getDiscipline(){
        return discipline;
    }
}
