//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import org.javatuples.Quartet;
import org.javatuples.Triplet;
import ui.Disciplines;
import ui.Status;

import java.util.ArrayList;
import java.util.HashMap;

public class Competition {
    String competitionName;


    //Double for time, int for rank, Enum for Discipline
    ArrayList<Quartet<CompetitiveMember,Double, Integer, Enum<Disciplines>>> results = new ArrayList<>();


    public Competition(String name) {
        competitionName = name;
    }

    public void addResult(Quartet<CompetitiveMember,Double, Integer, Enum<Disciplines>> result){
        results.add(result);
    }

    public Quartet getResult(){
       return results.get(0);
    }
}
