package domain;

import member.Member;
import member.MemberList;
import ui.UserInterface;

import java.util.ArrayList;

public class Controller {
    UserInterface ui = new UserInterface();
    MemberList memberList = new MemberList();
    Calculation calculation = new Calculation();

    public void start(){
        Member member = new Member("Torben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24,12,2000);
        Member member2 = new Member("Søren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10,10,1966);
        Member member3 = new Member("Tobias Vold", "98747723489", "Tobiases@mail.com", "TobyAllé 31", 1,1,1999);
        Member member4 = new Member("Finn Finsen", "8888888", "Finns@mail.com", "Finnminvej 99", 24,9,1920);
        System.out.println(member.calculateAge());
        System.out.println(member.getBirthDate());
        System.out.println(member.getMembershipNumber());
        memberList.addMember(member);
        memberList.addMember(member2);
        memberList.addMember(member3);
        memberList.addMember(member4);
        ui.printMemberLists();
    }

    public String userInputString(){
        return ui.userInputString();
    }

    public ArrayList<Member> memberList(){
        return memberList.getMemberList();
    }
}
