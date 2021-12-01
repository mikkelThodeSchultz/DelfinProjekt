package member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MemberList {
    private ArrayList<Member> memberList = new ArrayList<>();
    private Member selectedMember = null;


    public void addMember(Member member) {
        memberList.add(member);
    }

    public void removeMember() {
       memberList.remove(selectedMember);
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

    public String editName(String nameChange){
        String oldName = selectedMember.getName();
        selectedMember.setName(nameChange);
        return oldName;
    }

    public String editAddress(String addressChange){
        String oldAddress = selectedMember.getHomeAddress();
        selectedMember.setHomeAddress(addressChange);
        return oldAddress;
    }

    public String editEmail(String emailChange){
        String oldEmail = selectedMember.getEmail();
        selectedMember.setEmail(emailChange);
        return oldEmail;
    }

    public String editPhoneNumber(String phoneNumberChange){
        String oldPhoneNumber = selectedMember.getPhoneNumber();
        selectedMember.setPhoneNumber(phoneNumberChange);
        return oldPhoneNumber;
    }

    public String editBirthDate(LocalDate birthDateChange){
      String oldDate = selectedMember.oldDateToString();
      selectedMember.setBirthDate(birthDateChange);
      return oldDate;
    }

    public void editMembershipStatus(){
        selectedMember.setActive();
    }

    public String isActiveAsString(){
        String status = "";
        if (selectedMember.getIsActive() == false){
            status = "passivt";
        } else {
            status = "aktivt";
        }
        return "Medlemsskabet er " + status + ".\n";
    }

    public void editLevel(){
        //skal kunne ændre fra motionist til konkurrence og omvendt
        //skal oprettes et nyt opbjekt-medlem for hver gang
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

    public String newDateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd–MM–yyyy");
        String formattedDateTime = date.format(formatter);
        return formattedDateTime;
    }
}
