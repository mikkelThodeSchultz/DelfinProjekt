//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member2;

import java.time.LocalDateTime;

public class BestTrainingResult {
    double time;
    LocalDateTime dateBestResult;
    String discipline;

    public BestTrainingResult(){}

    public BestTrainingResult(LocalDateTime dateBestResult, double bestTime, String disciplin) {
        this.dateBestResult = dateBestResult;
        this.time = bestTime;
        this.discipline = disciplin;
    }

    public double getTime() {
        return time;
    }
    public String getDiscipline(){
        return discipline;
    }
}
