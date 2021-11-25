package member;

import domain.Controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;

public class MemberList {
    private ArrayList<Member> memberList = new ArrayList<>();
    private Member selectedMember = null;

    public void addMember(Member member){
        memberList.add(member);
    }

    public void removeMember(Member member){
        memberList.remove(member);
    }

    public Member findMember(String userInputSearch) {
        for (Member member : memberList) {
            if (member.matches(userInputSearch)) {
                selectedMember = member;
            }
        }
        return selectedMember;
    }

    public void selectMember(){
    }

    public void editMember(){
    }

    private String generateMembershipNumber(Member member) {
        String space = " ";
        String firstTwo = "";
        String lastTwo = "";
        Random random = new Random();
        int numbers = random.nextInt(10000);
        String numbersFormat = String.format("%04d", numbers);
        firstTwo = member.getName().substring(0, 2).toLowerCase();
        lastTwo = member.getName().substring(member.getName().lastIndexOf(space) + 1, member.getName().lastIndexOf(space) + 3).toLowerCase();
        return firstTwo + lastTwo + numbersFormat;
    }

    public int calculateAge(Member member) {
        LocalDate currentDate = LocalDate.now();
        return member.getBirthDate() != null ? Period.between(member.getBirthDate(), currentDate).getYears() : -1;
    }


    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void membersFromController(ArrayList fileMembers){
       memberList = fileMembers;
    }

    public String printMemberLists(){
        String temp = "";
        for (int i = 0; i < memberList.size(); i++) {
            temp += memberList.get(i) + "\n";
        }
        return temp;
    }
}
