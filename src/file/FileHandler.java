//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package file;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import domain.User;
import member.*;
import member.StandardMember;

//@author Mark Stone

public class FileHandler {

    private final String STANDARD_MEMBERS = "data/standard_members.json";
    private final String COMPETITIVE_MEMBERS = "data/competetive_members.json";
    private final String TRAINERS = "data/trainers.json";
    private final String COMPETITIONS = "data/competitions.json";
    private final String USERS = "data/users.json";


    public FileHandler() {}

    public void storeData(List<Member> members, List<Competition> comps, List<User> users) throws IOException {
        ArrayList<Member> standardMembers = new ArrayList<>();
        ArrayList<Member> competitiveMember = new ArrayList<>();
        ArrayList<Member> trainer = new ArrayList<>();
        for (Member member : members){
            if(member instanceof StandardMember){
                standardMembers.add(member);
            }
            else if(member instanceof CompetitiveMember){
                competitiveMember.add(member);
            }
            else if(member instanceof Trainer){
                trainer.add(member);
            }
        }
        saveToFile(convertMembersToJson(standardMembers), STANDARD_MEMBERS);
        saveToFile(convertMembersToJson(competitiveMember), COMPETITIVE_MEMBERS);
        saveToFile(convertMembersToJson(trainer), TRAINERS);
        saveToFile(convertCompsToJson(comps),COMPETITIONS );
        saveToFile(convertUsersToJson(users), USERS);
    }

    public ArrayList<Member> getMembersFromFile() {
        ArrayList<Member> storedMembers = new ArrayList<>();
        storedMembers.addAll(readMembersFromFile(STANDARD_MEMBERS, "standard_member"));
        storedMembers.addAll(readMembersFromFile(COMPETITIVE_MEMBERS, "competetive_member"));
        storedMembers.addAll(readMembersFromFile(TRAINERS, "trainer"));
        return storedMembers;
    }

    public ArrayList<Competition> getCompsFromFile(){
        ArrayList<Competition> comps = new ArrayList<>();
        comps.addAll(readCompsFromFile(COMPETITIONS));
        return comps;
    }

    public ArrayList<User> getUsersFromFile(){
        ArrayList<User> users = new ArrayList<>();
        users.addAll(readUsersFromFile(USERS));
        return users;
    }

    private String convertMembersToJson(List<Member> members) throws JsonProcessingException {
        JsonNode node = Json.toJson(members);
        return Json.prettyPrint(node);
    }

    private String convertCompsToJson(List<Competition> comps) throws JsonProcessingException {
        JsonNode node = Json.toJson(comps);
        return Json.prettyPrint(node);
    }

    private String convertUsersToJson(List<User> users) throws JsonProcessingException {
        JsonNode node = Json.toJson(users);
        return Json.prettyPrint(node);
    }


    private void saveToFile(String stringToSave, String filePath) throws IOException {
        File file = new File(filePath);
        PrintStream ps = new PrintStream(file);
        ps.println(stringToSave);
    }

    private ArrayList<String> getLinesFromFile(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList();
        File file = new File(filePath);
        Scanner load = new Scanner(file);

        while(load.hasNextLine()) {
            lines.add(load.nextLine());
        }

        return lines;
    }

    private ArrayList<Member> readMembersFromFile(String file, String type) {
        List<String> lines;
        try {
            lines = getLinesFromFile(file);
            if (lines.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String line : lines) {
                    stringBuilder.append(line).append('\n');
                }
                if(type == "standard_member"){
                    List<StandardMember> members = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<>() {
                });
                    return new ArrayList<>(members);
                }
                else if(type == "competetive_member"){
                    List<CompetitiveMember> members = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<>() {
                    });
                    return new ArrayList<>(members);
                }
                else if(type == "trainer"){
                    List<Trainer> members = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<>() {
                    });
                    return new ArrayList<>(members);
                }
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    private ArrayList<Competition> readCompsFromFile(String file) {
        List<String> lines;
        try {
            lines = getLinesFromFile(file);
            if (lines.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String line : lines) {
                    stringBuilder.append(line).append('\n');
                }
                List<Competition> comps = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<>() {
                });
                return new ArrayList<>(comps);
            }
        } catch (IOException e) {
            System.out.println("Error");
            return new ArrayList<>();
        }
        System.out.println("Error");
        return new ArrayList<>();
    }

    private ArrayList<User> readUsersFromFile(String file) {
        List<String> lines;
        try {
            lines = getLinesFromFile(file);
            if (lines.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String line : lines) {
                    stringBuilder.append(line).append('\n');
                }
                List<User> users = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<>() {
                });
                return new ArrayList<>(users);
            }
        } catch (IOException e) {
            System.out.println("Error");
            return new ArrayList<>();
        }
        System.out.println("Error");
        return new ArrayList<>();
    }
}
