package file;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public void storeMember(List<Member> orders) throws IOException {
        saveToFile(convertMembersToJson(orders), MEMBERS);
    }

    public List<String> getMembersFromFile() {
        try {
            return getLinesFromFile(MEMBERS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
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


    // will take a file and return the lines in the file
    private ArrayList<String> getLinesFromFile(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        File file = new File(filePath);

        Scanner load = new Scanner(file);
        while (load.hasNextLine()) {
            lines.add(load.nextLine());
        }
        return lines;
    }
}
