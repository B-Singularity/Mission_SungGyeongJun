package com.ll;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Wisdomdelete {
    private static final String JSON_FILE_PATH = "src/main/resources/wisdoms.json";
    private static Scanner scanner = new Scanner(System.in);

    private static JsonArray readJsonFile() {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            return new Gson().fromJson(reader, JsonArray.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteById(HashMap<Integer, Wisdom> wisdomMap) {
        System.out.print("삭제할 명언의 ID를 입력하세요: ");
        int idToDelete = scanner.nextInt();

        if (wisdomMap.containsKey(idToDelete)) {
            wisdomMap.remove(idToDelete);
            adjustIdsAfterDeletion(wisdomMap, idToDelete);
            updateJsonFile(wisdomMap);
            System.out.println(idToDelete + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(idToDelete + "번 명언을 찾을 수 없습니다.");
        }
    }

    private static void adjustIdsAfterDeletion(HashMap<Integer, Wisdom> wisdomMap, int deletedId) {
        for (int id : wisdomMap.keySet()) {
            if (id > deletedId) {
                Wisdom wisdom = wisdomMap.get(id);
                wisdomMap.remove(id);
                wisdom.setId(id - 1);
                wisdomMap.put(id - 1, wisdom);
            }
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