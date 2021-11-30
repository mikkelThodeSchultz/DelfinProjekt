//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import java.util.ArrayList;

import ui.Disciplines;
import ui.Role;

public class CompetitiveMember extends Member {
    ArrayList<BestTrainingResult> bestTrainingResults = new ArrayList();
    ArrayList<Disciplines> disciplines = new ArrayList<>();
    Role role;

    public CompetitiveMember(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
        super (name, phoneNumber, email, homeAddress, day, month, year);
        this.role = Role.COMPETITIVE;
    }

    public void addDisciplinesToList() {
        disciplines.add(Disciplines.BREASTSTROKE);
        disciplines.add(Disciplines.CRAWL);
        disciplines.add(Disciplines.BUTTERFLY);
        disciplines.add(Disciplines.BACK_CRAWL);
    }

    //metode der fanger disciplin dubletter




    //USED FOR JSON. DO NOT DELETE
    public CompetitiveMember(){

    }

}
