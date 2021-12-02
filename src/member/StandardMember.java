//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package member;

import ui.Role;

public class StandardMember extends Member {

    private Role role;

    public StandardMember(String name, String phoneNumber, String email, String homeAddress, int day, int month, int year) {
        super (name, phoneNumber, email, homeAddress, day, month, year);
        role = Role.STANDARD;
    }

    //NEEDED FOR JSON! DO NOT DELETE
    public StandardMember(){}


    public String toString(){
        String status = null;
        if (getIsActive()){
            status = setIsActive();
        } else {
            status = setIsPassive();
        }
        return super.toString() + " – " + role.toString() + " - (" + status + ")";
    }

}
