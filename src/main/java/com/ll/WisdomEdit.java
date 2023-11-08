package com.ll;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class WisdomEdit {
    private static final String JSON_FILE_PATH = "src/main/resources/wisdoms.json";
    private static Scanner scanner = new Scanner(System.in);

    public static void edit(HashMap<Integer, Wisdom> wisdomMap) {
        System.out.println("수정할 명언의 ID를 입력하세요: ");
        int idToEdit = scanner.nextInt();

        if (wisdomMap.containsKey(idToEdit)) {
            Wisdom wisdomToEdit = wisdomMap.get(idToEdit);

            System.out.println("현재 명언: " + wisdomToEdit.getSaying());
            System.out.println("수정할 명언: ");
            String newSaying = scanner.next();

            System.out.println("현재 작가: " + wisdomToEdit.getArtist());
            System.out.println("수정할 작가: ");
            String newArtist = scanner.next();

            wisdomToEdit.setSaying(newSaying);
            wisdomToEdit.setArtist(newArtist);

            updateJsonFile(wisdomMap);
            System.out.println(idToEdit + "번 명언이 수정되었습니다.");
        } else {
            System.out.println(idToEdit + "번 명언을 찾을 수 없습니다.");
        }
    }

    private static void updateJsonFile(HashMap<Integer, Wisdom> wisdomMap) {
        JsonArray jsonArray = new JsonArray();
        for (Wisdom wisdom : wisdomMap.values()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", wisdom.getId());
            jsonObject.addProperty("saying", wisdom.getSaying());
            jsonObject.addProperty("artist", wisdom.getArtist());
            jsonArray.add(jsonObject);
        }

        try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("JSON 파일을 업데이트할 수 없습니다.");
        }
    }
}
