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

    public Member[] findMember(String userInputSearch) {
        ArrayList<Member> foundMembers = new ArrayList<>();
        for (Member member : memberList) {
            if (member.matches(userInputSearch)) {
                foundMembers.add(member);
            }
        }
        return foundMembers.toArray(new Member[0]);
    }

    public void setSelectedMember(Member member) {
        selectedMember = member;
    }

    //Rettes: navn, adresse, telefonnummer, birthday, aktiv/passiv, motionist/konkurrence-svømmer

    public String editName(String nameChange){
        String oldName = selectedMember.getName();
        selectedMember.setName(nameChange);
        return oldName;
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void membersFromController(ArrayList<Member> fileMembers) {
        for (Member fileMember : fileMembers) {
            memberList.add(fileMember);
        }
    }

    public String printMemberList() {
        String allMembers = "";
        for (int i = 0; i < memberList.size(); i++) {
            allMembers += memberList.get(i) + "\n";
        }
        return allMembers;
    }
}
