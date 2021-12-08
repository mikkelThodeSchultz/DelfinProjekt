package domain;

import junit.framework.TestCase;
import member.Member;
import member.StandardMember;

import java.util.ArrayList;

public class CalculationTest extends TestCase {

    public void testCalculatePassiveContingent() {
        //Arrange
        Calculation calculation = new Calculation();
        double expected = 500;
        //Act
        double result = calculation.calculateContingent(20,false);
        //Assert
        assertEquals(expected,result);
    }

    public void testCalculateJuniorActiveContingent() {
        Calculation calculation = new Calculation();
        double expected = 1000;
        double result = calculation.calculateContingent(17,true);
        assertEquals(expected,result);
    }

    public void testCalculateSeniorActiveContingent() {
        Calculation calculation = new Calculation();
        double expected = 1600;
        double result = calculation.calculateContingent(20,true);
        assertEquals(expected,result);
    }

    public void testCalculateSeniorActiveContingentWithDiscount() {
        Calculation calculation = new Calculation();
        double expected = 1200;
        double result = calculation.calculateContingent(60,true);
        assertEquals(expected,result);
    }

    public void testCalculateContingentForMultiplePeople() {
        Calculation calculation = new Calculation();
        ArrayList<Member> memberList = new ArrayList<>();
        StandardMember member = new StandardMember("Torben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        StandardMember member2 = new StandardMember("Søren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10, 10, 1966);
        StandardMember member3 = new StandardMember("Tobias Vold", "98747723489", "Tobiases@mail.com", "TobyAllé 31", 1, 1, 1999);
        StandardMember member4 = new StandardMember("Finn Finsen", "8888888", "Finns@mail.com", "Finnminvej 99", 24, 9, 1920);
        StandardMember member5 = new StandardMember("Jim Henry", "67453219", "Jims@mail.com", "Jimminvej 89", 14, 6, 2008);
        memberList.add(member);
        memberList.add(member2);
        memberList.add(member3);
        memberList.add(member4);
        memberList.add(member5);
        double expected = 7000;
        double result = calculation.calculateContingentForMultipleMembers(memberList);
        assertEquals(expected,result);
    }

    public void testDemandPayment() {
        Calculation calculation = new Calculation();
        ArrayList<Member> memberList = new ArrayList<>();
        StandardMember member = new StandardMember("Torben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        StandardMember member2 = new StandardMember("Søren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10, 10, 1966);
        memberList.add(member);
        memberList.add(member2);
        calculation.demandPayment(memberList);
        boolean expected = false;
        boolean result = member.getHasPaid();
        assertEquals(expected, result);

    }

    public void testListOfPeopleWhoOwe() {
        Calculation calculation = new Calculation();
        ArrayList<Member> memberList = new ArrayList<>();
        StandardMember member = new StandardMember("Torben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        StandardMember member2 = new StandardMember("Søren Kristiansen", "45678910", "Sørensmail@mail.com", "Sørensvej 14", 10, 10, 1966);
        member.setHasPaid(false);
        member2.setHasPaid(false);
        memberList.add(member);
        memberList.add(member2);
        ArrayList<Member> membersWhoOwe = calculation.listOfMembersWhoOwe(memberList);
        assertEquals(membersWhoOwe,memberList);
    }

    public void testsetMembershipPayedStatusToReverse() {
        Calculation calculation = new Calculation();
        StandardMember member = new StandardMember("Torben Trucker", "12345678", "Torbensmail@mail.com", "torbensvej 31", 24, 12, 2000);
        member.setHasPaid(true);
        boolean expected = false;
        boolean result = calculation.setMembershipPayedStatusToReverse(member);
        assertEquals(expected, result);

    }
}