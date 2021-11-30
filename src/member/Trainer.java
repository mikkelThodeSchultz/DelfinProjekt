//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import ui.Role;

import java.util.ArrayList;

public class Trainer extends Member {
    private ArrayList<CompetitiveMember> competitiveSwimmers = new ArrayList();
    private Role role;

    public Trainer(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
        super (name, phoneNumber, email, homeAddress, day, month, year);
        this.role = Role.TRAINER;
    }


    //USED FOR JSON. DO NOT DELETE
    public Trainer(){

    }

}
