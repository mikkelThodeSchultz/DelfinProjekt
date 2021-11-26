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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import member.Member;
import member.StandardMember;

public class FileHandler {
    private final String STANDARD_MEMBERS = "data/standard_members.json";
    private final String COMPETETIVE_MEMBERS = "data/competetive_members.json";
    private final String TRAINERS = "data/trainers.json";
    private final String TEST = "data/members.json";


    public FileHandler() {
    }

    public void storeMember(List<Member> members, String memberType) throws IOException {
        switch (memberType){
            case "STANDARD_MEMBER" -> saveToFile(convertMembersToJson(members), STANDARD_MEMBERS);
            case "COMPETETIVE_MEMBERS" -> saveToFile(convertMembersToJson(members), COMPETETIVE_MEMBERS);
            case "TRAINERS" -> saveToFile(convertMembersToJson(members), TRAINERS);
        }
    }

    public ArrayList<Member> getMembersFromFile(String memberType) {
        ArrayList<Member> storedMembers = null;
        switch (memberType) {
            case "STANDARD_MEMBER" -> storedMembers = getStoredFromFile(STANDARD_MEMBERS);
            case "COMPETETIVE_MEMBERS" -> storedMembers = getStoredFromFile(COMPETETIVE_MEMBERS);
            case "TRAINERS" -> storedMembers =  getStoredFromFile(TRAINERS);
        }
        return storedMembers;
    }

    private String convertMembersToJson(List<Member> members) throws JsonProcessingException {
        JsonNode node = Json.toJson(members);
        return Json.prettyPrint(node);
    }

    private void saveToFile(String stringToSave, String filePath) throws IOException {
        File file = new File(filePath);
        PrintStream ps = new PrintStream(file, StandardCharsets.UTF_8);
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

    private ArrayList<Member> getStoredFromFile(String file) {
        List<String> lines;
        try {
            lines = getLinesFromFile(file);
            if (lines.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String line : lines) {
                    stringBuilder.append(line).append('\n');
                }

                List<StandardMember> members = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<>() {
                });
                return new ArrayList<>(members);
            }
        } catch (IOException e) {
            System.out.println("Error");
            return new ArrayList<>();
        }
        System.out.println("Error");
        return new ArrayList<>();
    }
}
