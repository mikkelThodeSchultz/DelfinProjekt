package member;
import java.util.ArrayList;

//@author Mark Stone

public class Competition {
    private String competitionName;
    private ArrayList<CompetitionResult> results = new ArrayList<>();

    //Tom constructor til Json
    public Competition(){}

    public Competition(String name) {
        competitionName = name;
    }

    public void addResult(CompetitionResult result){
        results.add(result);
    }

    public ArrayList<CompetitionResult> getResults(){
        return results;    }

    public String getCompetitionName() {
        return competitionName;
    }
}
