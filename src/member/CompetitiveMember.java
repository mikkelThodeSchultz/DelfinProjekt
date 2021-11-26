//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import java.util.ArrayList;

import ui.Disciplines;
import ui.Status;

public class CompetitiveMember extends Member {
    ArrayList<Competition> competitions = new ArrayList();
    ArrayList<BestTrainingResult> bestTrainingResults = new ArrayList();
    ArrayList<Disciplines> disciplines = new ArrayList<>();

    //TODO lav metode der fanger disciplin dubletter

    public CompetitiveMember() {
    }
}
