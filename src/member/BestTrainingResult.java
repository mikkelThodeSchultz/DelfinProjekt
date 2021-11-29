//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import java.time.LocalDateTime;
import ui.Status;

public class BestTrainingResult {
    LocalDateTime dateBestResult;
    double bestTime;
    Status disciplin;

    public BestTrainingResult(LocalDateTime dateBestResult, double bestTime, Status disciplin) {
        this.dateBestResult = dateBestResult;
        this.bestTime = bestTime;
        this.disciplin = disciplin;
    }
}
