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

public class FileHandler {
    private final String MEMBERS = "data/members.json";

    public FileHandler() {
    }

    public void storeMember(List<Member> members) throws IOException {
        this.saveToFile(this.convertMembersToJson(members), MEMBERS);
    }

    public ArrayList<Member> getMembersFromFile() {
        return getStoredFromFile(MEMBERS);
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
              //  System.out.println(stringBuilder);

                List<Member> members = Json.fromJsonToArray(stringBuilder.toString(), new TypeReference<>() {
                });
                System.out.println(members);
                return new ArrayList<>(members);
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
