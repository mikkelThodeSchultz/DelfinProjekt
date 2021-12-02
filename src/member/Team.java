package member;

import java.util.ArrayList;

public class Team {
    private ArrayList<String> teamMembers = new ArrayList<>();
    private String teamName;
    private String discipline;

    public Team(ArrayList<String> teamMembers, String teamName, String discipline) {
        this.teamMembers = teamMembers;
        this.teamName = teamName;
        this.discipline = discipline;
    }

    public Team(){}
}

