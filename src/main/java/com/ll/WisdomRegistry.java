package com.ll;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class WisdomRegistry {
    private Scanner scanner;
    String saying;
    String artist;
    private static HashMap<Integer, Wisdom> wisdomMap = new HashMap<>();

    public WisdomRegistry() {
        scanner = new Scanner(System.in);
        loadWisdomsFromJson();
    }

    public HashMap<Integer, Wisdom> getWisdomMap() {
        return wisdomMap;
    }

    public static void loadWisdomsFromJson() {
        try (FileReader reader = new FileReader("src/main/resources/wisdoms.json")) {
            JsonArray jsonArray = new Gson().fromJson(reader, JsonArray.class);

            wisdomMap.clear();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject wisdomObject = jsonArray.get(i).getAsJsonObject();
                int id = wisdomObject.get("id").getAsInt();
                String saying = wisdomObject.get("saying").getAsString();
                String artist = wisdomObject.get("artist").getAsString();
                wisdomMap.put(id, new Wisdom(id, saying, artist));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerWiseSaying() {
        System.out.printf("명언: ");
        saying = scanner.nextLine();
        System.out.printf("작가: ");
        artist = scanner.nextLine();
        int Id = wisdomMap.size() + 1;
        Wisdom wisdom = new Wisdom(Id, saying, artist);
        wisdomMap.put(Id, wisdom);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(wisdomMap.values());

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/wisdoms.json");
            JsonArray jsonArray = new JsonArray();
            for (Wisdom w : wisdomMap.values()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", w.getId());
                jsonObject.addProperty("saying", w.getSaying());
                jsonObject.addProperty("artist", w.getArtist());
                jsonArray.add(jsonObject);
            }
            fileWriter.write(jsonArray.toString());
            fileWriter.close();

            System.out.println(Id + "번 명언이 등록되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("명언을 저장할 수 없습니다.");
        }
    }
}






