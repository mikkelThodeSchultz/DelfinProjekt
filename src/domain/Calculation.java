//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain;

import member.Member;

import java.util.ArrayList;

// @author Mikkel Thode Schultz

public class Calculation {

    public Calculation() {
    }
    //Public for at kunne lave tests.
    public double calculateContingent(int age, boolean isActive) {
        int junior = 17;
        int senior = 18;
        int seniorDiscount = 60;
        double passiveMembership = 500;
        double activeJuniorMembership = 1000;
        double activeSeniorMembership = 1600;
        double discount = 0.75;
        double contingentSum;
        if (!isActive) {
            contingentSum = passiveMembership;
        } else if (age <= junior) {
            contingentSum = activeJuniorMembership;
        } else if (age > senior && age < seniorDiscount) {
            contingentSum = activeSeniorMembership;
        } else {
            contingentSum = activeSeniorMembership * discount;
        }
        return contingentSum;
    }

    public ArrayList<Member> listOfMembersWhoOwe(ArrayList<Member> members) {
        ArrayList<Member> membersWhoOwe = new ArrayList<>();
        for (Member member : members) {
            if (!member.getHasPaid()) {
                membersWhoOwe.add(member);
            }
        }
        return membersWhoOwe;
    }

    public double calculateContingentForMultipleMembers(ArrayList<Member> members) {
        double contingentSumForMultipleMembers = 0;
        for (Member member : members) {
            contingentSumForMultipleMembers += calculateContingent(member.getAge(), member.getIsActive());
        }
        return contingentSumForMultipleMembers;
    }

    public void demandPayment(ArrayList<Member> memberList) {
        for (Member member:memberList) {
            member.setHasPaid(false);
        }
    }

    public boolean setMembershipPayedStatusToReverse(Member member) {
        if (member.getHasPaid()) {
            member.setHasPaid(false);
            return false;
        } else member.setHasPaid(true);
        return true;
    }
}
