//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import ui.Role;

public class StandardMember extends Member {

    Role role;

    public StandardMember(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
        super (name, phoneNumber, email, homeAddress, day, month, year);
        this.role = Role.STANDARD;
    }

    //NEEDED FOR JSON! DO NOT DELETE
    public StandardMember(){}
}
