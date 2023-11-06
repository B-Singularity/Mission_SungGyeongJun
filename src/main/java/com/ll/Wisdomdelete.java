package com.ll;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void deleteById() {
        System.out.println("삭제할 명언의 ID를 입력하세요: ");
        int idToDelete = scanner.nextInt();

        JsonArray jsonArray = readJsonFile();

        if (jsonArray != null) {
            boolean deleted = false;
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject wisdomObject = jsonArray.get(i).getAsJsonObject();
                int wisdomId = wisdomObject.get("id").getAsInt();
                if (wisdomId == idToDelete) {
                    jsonArray.remove(i);
                    deleted = true;
                    for (int j = i; j < jsonArray.size(); j++) {
                        wisdomObject = jsonArray.get(j).getAsJsonObject();
                        wisdomId = wisdomObject.get("id").getAsInt();
                        wisdomObject.addProperty("id", wisdomId - 1);
                    }
                    break;
                }
            }

            if (deleted) {
                writeJsonFile(jsonArray);
                System.out.println(idToDelete + "번 명언이 삭제되었습니다.");
                WisdomRegistry.loadWisdomsFromJson();
            } else {
                System.out.println(idToDelete + "번 명언을 찾을 수 없습니다.");
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
