package com.ll;

import java.util.HashMap;

public class WisdomShow {
    public static void show(HashMap<Integer, Wisdom> wisdomMap) {
        if (wisdomMap.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        }
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (Wisdom wisdom : wisdomMap.values()) {
            System.out.println(wisdom.getId() + " / " + wisdom.getArtist() + " / " + wisdom.getSaying());
        }
    }

}
