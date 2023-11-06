package com.ll;

import java.util.List;

public class WisdomShow {
    public static void show(List<Wisdom> wisdomList) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (Wisdom wisdom : wisdomList) {
            System.out.println(wisdom.getId() + " / " + wisdom.getArtist() + " / " + wisdom.getSaying());
        }
    }

}
