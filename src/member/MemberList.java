package member;

import domain.Controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;

public class MemberList {
    private ArrayList<Member> memberList = new ArrayList<>();
    private Member selectedMember = null;

    public void addMember(Member member) {
        memberList.add(member);
    }

    public void removeMember(Member member) {
        memberList.remove(member);
    }

    public Member findMember(String userInputSearch) {
        for (Member member : memberList) {
            if (member.matches(userInputSearch)) {
                selectedMember = member;
                System.out.println(selectedMember);
            }
        }
        return selectedMember;
    }

    public void selectMember() {
    }

    public void editMember() {
    }


    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void membersFromController(ArrayList<Member> fileMembers) {
        for (Member fileMember : fileMembers) {
            memberList.add(fileMember);
        }
    }

    public String printMemberLists() {
        String temp = "";
        for (int i = 0; i < memberList.size(); i++) {
            temp += memberList.get(i) + "\n";
        }
        return temp;
    }
}
