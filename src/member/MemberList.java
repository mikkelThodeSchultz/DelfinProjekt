package member;

import domain.Controller;

import java.util.ArrayList;

public class MemberList {
    ArrayList<Member> memberList = new ArrayList<>();
    private Member selectedMember = null;


    public void addMember(Member member){
        memberList.add(member);
    }

    public void removeMember(Member member){
        memberList.remove(member);
    }

    public void findMember(){
        Controller controller = new Controller();
        String tempString = controller.userInputString();
        for (Member member: memberList) {
            if (member.getMembershipNumber().equals(tempString)){
                selectedMember = member;
            }
        }
    }


    public void editMember(){

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
