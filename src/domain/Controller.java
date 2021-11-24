//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain;

import file.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import member.Member;
import member.MemberList;
import ui.UserInterface;

public class Controller {
    UserInterface ui = new UserInterface();
    MemberList memberList = new MemberList();
    Calculation calculation = new Calculation();
    FileHandler FileHandler = new FileHandler();
    private ArrayList<Member> storedMembers;


    public Controller() {
    }

    public void start() {
        Member member = new Member("Torben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        Member member2 = new Member("Søren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10, 10, 1966);
        Member member3 = new Member("Tobias Vold", "98747723489", "Tobiases@mail.com", "TobyAllé 31", 1, 1, 1999);
        Member member4 = new Member("Finn Finsen", "8888888", "Finns@mail.com", "Finnminvej 99", 24, 9, 1920);
        Member member5 = new Member("Jim Henry", "67453219", "Jims@mail.com", "Jimminvej 89", 14, 6, 2008);
        System.out.println(member.calculateAge());
        System.out.println(member.getBirthDate());
        System.out.println(member.getMembershipNumber());
        memberList.addMember(member);
        memberList.addMember(member2);
        memberList.addMember(member3);
        memberList.addMember(member4);
        memberList.addMember(member5);

        //Henter members fra fil
        /*storedMembers = FileHandler.getMembersFromFile();
        sendStoredMembers(storedMembers);
        ui.printMemberLists(memberList.printMemberLists());*/

        System.out.println(calculateTotalIncome(memberList));

        try {
            FileHandler.storeMember(memberList.getMemberList());
        } catch (IOException e) {
            System.out.println("Failed to store members");
        }
    }

    public double calculateTotalIncome(MemberList memberList){
        double totalSum = 0;
        for (int i = 0; i < memberList.getMemberList().size(); i++) {
            totalSum += calculateMembershipFee(memberList.getMemberList().get(i));
        }
        /*for (Member member : memberList) {
           totalSum += calculateMembershipFee(member);
        }*/
        return totalSum;
    }

    public double calculateMembershipFee(Member member){
        return calculation.calculateContingent(member.getAge(), member.getIsActive());
    }

    public String userInputString() {
        return ui.userInputString();
    }

    public ArrayList<Member> memberList() {
        return memberList.getMemberList();
    }

    public void sendStoredMembers(ArrayList storedMembers){
        memberList.membersFromController(storedMembers);
        storedMembers.clear();
    }
}
