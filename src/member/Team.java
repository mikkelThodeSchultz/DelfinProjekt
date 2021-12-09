package member;

import ui.Disciplines;

import java.util.ArrayList;

//@author Mark Stone

public class Team {
    private ArrayList<String> teamMembers = new ArrayList<>();
    private String teamName;
    private Disciplines discipline;

    //NEEDED FOR JSON! DO NOT DELETE
    public Team(){}

    public Team(ArrayList<String> teamMembers, String teamName, Disciplines discipline) {
        this.teamMembers = teamMembers;
        this.teamName = teamName;
        this.discipline = discipline;
    }

    public Disciplines getDiscipline(){
        return discipline;
    }

    public void setDiscipline(Disciplines discipline){
        this.discipline = discipline;
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
}

