//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member2;



import java.util.ArrayList;

public class Competition {
    String competitionName;
    ArrayList<CompetitonResult> results = new ArrayList<>();


    public Competition(){}

    public Competition(String name) {
        competitionName = name;
    }

    public void addResult(CompetitonResult result){
        results.add(result);
    }

    public ArrayList<CompetitonResult> getResults(){
        return results;    }

    public String getCompetitionName() {
        return competitionName;
    }
}
