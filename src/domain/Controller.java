//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain;

import file.FileHandler;
import java.io.IOException;
import java.util.ArrayList;
import member.Member;
import member.MemberList;
import ui.UserInterface;

public class Controller {
    ArrayList<String> memberLists = new ArrayList();
    UserInterface ui = new UserInterface();
    MemberList memberList = new MemberList();
    Calculation calculation = new Calculation();
    FileHandler FileHandler = new FileHandler();

    public Controller() {
    }

    public void start() throws IOException {
        Member member = new Member("Torben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        Member member2 = new Member("Søren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10, 10, 1966);
        Member member3 = new Member("Tobias Vold", "98747723489", "Tobiases@mail.com", "TobyAllé 31", 1, 1, 1999);
        Member member4 = new Member("Finn Finsen", "8888888", "Finns@mail.com", "Finnminvej 99", 24, 9, 1920);
        System.out.println(member.calculateAge());
        System.out.println(member.getBirthDate());
        System.out.println(member.getMembershipNumber());
        this.memberList.addMember(member);
        this.memberList.addMember(member2);
        this.memberList.addMember(member3);
        this.memberList.addMember(member4);
        this.ui.printMemberLists();
        this.FileHandler.storeMember(this.memberList.getMemberList());
    }

    public String userInputString() {
        return this.ui.userInputString();
    }

    public ArrayList<Member> memberList() {
        return this.memberList.getMemberList();
    }
}
