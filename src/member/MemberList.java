package member;

import domain.Controller;

import java.util.ArrayList;

public class MemberList {
    ArrayList<Member> memberLists = new ArrayList<>();
    private Member selectedMember = null;


    public void addMember(Member member){
        memberLists.add(member);
    }

    public void removeMember(Member member){
        memberLists.remove(member);
    }

    public void findMember(){
        Controller controller = new Controller();
        String tempString = controller.userInputString();
        for (Member member:memberLists) {
            if (member.getMembershipNumber().equals(tempString)){
                selectedMember = member;
            }
        }
    }


    public void editMember(){

    }

    public ArrayList<Member> getMemberList() {
        return memberLists;
    }

    public String printMemberLists(){
        String temp = "";
        for (int i = 0; i < memberLists.size(); i++) {
            temp += memberLists.get(i) + "\n";
        }
        return temp;
    }

}
