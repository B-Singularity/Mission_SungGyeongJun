package com.ll;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WisdomEdit {
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

    public static void edit() {
        System.out.println("수정할 명언의 ID를 입력하세요: ");
        int idToEdit = scanner.nextInt();

        JsonArray jsonArray = readJsonFile();

        if(jsonArray != null) {
            boolean edited = false;
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject wisdomObject = jsonArray.get(i).getAsJsonObject();
                int wisdomId = wisdomObject.get("id").getAsInt();
                if (wisdomId == idToEdit) {
                    System.out.println("수정할 명언: ");
                    String newSaying = scanner.next();
                    System.out.println("수정할 작가: ");
                    String newArtist = scanner.next();

                    wisdomObject.addProperty("saying", newSaying);
                    wisdomObject.addProperty("artist", newArtist);

                    edited = true;
                    break;

                }
            }

            if (edited) {
                writeJsonFile(jsonArray);
                System.out.println(idToEdit + "번 명언이 수정되었습니다.");
                WisdomRegistry.loadWisdomsFromJson();
            } else {
                System.out.println(idToEdit + "번 명언을 찾을 수 없습니다.");
            }
        }
    }
    private static void writeJsonFile(JsonArray jsonArray) {
        try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("JSON 파일을 업데이트할 수 없습니다.");
        }
    }
}

