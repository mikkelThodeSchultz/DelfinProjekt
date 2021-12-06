package member;

import ui.Disciplines;

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
    public String getDiscipline(){
        return discipline;
    }

    public void setDiscipline(Disciplines discipline){
        this.discipline = discipline.toString();
    }

    public String getTeamName(){
        return teamName;
    }
    public ArrayList<String> getTeamMembers(){
        return teamMembers;
    }
    public void addMembers(ArrayList<String> memberToAdd){
        teamMembers.addAll(memberToAdd);
    }

    public void setTeamName(String newName){
        teamName = newName;
    }

    public Team(){}
}

