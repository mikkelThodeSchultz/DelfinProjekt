//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import ui.Role;

import java.util.ArrayList;

public class Trainer extends Member {
    private ArrayList<Team> Teams = new ArrayList<>();
    private Role role;

    public Trainer(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
        super(name, phoneNumber, email, homeAddress, day, month, year);
        role = Role.TRÆNER;
    }

    //USED FOR JSON. DO NOT DELETE
    public Trainer() {
    }

    public void addTeam(Team team) {
        Teams.add(team);
    }

    public Role getRole() {
        return role;
    }

    public ArrayList<Team> getTeams() {
        return Teams;
    }

    public void removeTeam(Team teamToRemove){
        Teams.remove(teamToRemove);
    }

    public String toString() {
        String status = null;
        if (getIsActive()) {
            status = setIsActive();
        } else {
            status = setIsPassive();
        }
        return super.toString() + " – " + role.toString() + " - (" + status + ")";
    }

}
