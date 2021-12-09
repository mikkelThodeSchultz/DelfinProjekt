package member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//@author Fie og Etienne

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

    public String getSelectedMemberName(){
        String justTheName = selectedMember.getName();
        return justTheName;
    }

    public String getSelectedMemberAddress(){
        String justTheAddress = selectedMember.getHomeAddress();
        return justTheAddress;
    }

    public String getSelectedMemberEmail(){
        String justTheEmail = selectedMember.getEmail();
        return justTheEmail;
    }

    public String getSelectedMemberPhoneNumber(){
        String justThePhoneNumber = selectedMember.getPhoneNumber();
        return justThePhoneNumber;
    }

    public String getSelectedMemberBirthDate(){
        String justTheBirthDate = selectedMember.oldDateToString();
        return justTheBirthDate;
    }

    public String getSelectedMembershipStatus(){
        String justTheMembershipStatus = selectedMember.getIsActiveAsString();
        return justTheMembershipStatus;
    }

    public String getSelectedMemberRole(){
        String justTheRole = selectedMember.getRole().toString();
        return justTheRole;
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
        if (!selectedMember.getIsActive()){
            status = "passivt";
        } else {
            status = "aktivt";
        }
        return "Medlemsskabet for " + selectedMember.getName() + " er nu " + status + ".\n";
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void membersFromController(ArrayList<Member> fileMembers) {
        memberList.addAll(fileMembers);
    }

    public String collectAllInfoString(){
        String info = "";
        info = "Navn: " + selectedMember.getName() + "\n" +
                "Medlemsnummer: " + selectedMember.getMembershipNumber() + "\n" +
                "Telefonnummer: " + selectedMember.getPhoneNumber() + "\n" +
                "E-mail: " + selectedMember.getEmail() + "\n" +
                "Adresse: " + selectedMember.getHomeAddress() + "\n" +
                "Fødselsdag: " + newDateToString(selectedMember.getBirthDate()) + "\n" +
                "Medlemsskabsstatus: " + selectedMember.getIsActiveAsString() + "\n" +
                "Svømmeniveau: " + selectedMember.getRole();
        return info;
    }

    public String getMemberListAsString() {
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

    public Member getSelectedMember() {
        return selectedMember;
    }
}
