//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain;

import member.Member;

import java.util.ArrayList;

public class Calculation {

    public Calculation() {
    }

    //Skal måske være private da linje 40 kan håndtere individder såvel som flere.
    public double calculateContingent(int age, boolean isActive) { //sender double til controller
        int junior = 17;
        int senior = 18;
        int seniorDiscount = 60;
        double passiveMembership = 500;
        double activeJuniorMembership = 1000;
        double activeSeniorMembership = 1600;
        double discount = 0.75;
        double contingentSum = 0;

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

}
