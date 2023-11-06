package com.ll;
import java.util.Scanner;

public class WiseSaying {
    String command;
    String artist;
    Scanner scanner;
    WisdomRegistry wisdomRegistry;

    public WiseSaying() {
        scanner = new Scanner(System.in);
        wisdomRegistry = new WisdomRegistry();
    }

    void start() {
        while (true) {
            System.out.printf("명령) ");
            this.command = scanner.nextLine();
            if (command.equalsIgnoreCase("등록")) {
                wisdomRegistry.registerWiseSaying();
            } else if (command.equalsIgnoreCase("종료")) {
                break;
            } else if (command.equalsIgnoreCase("목록")) {
                WisdomShow.show(wisdomRegistry.getWisdomMap());
            } else if (command.equalsIgnoreCase("삭제")) {
                Wisdomdelete.deleteById(wisdomRegistry.getWisdomMap());
                wisdomRegistry.loadWisdomsFromJson();
            } else if (command.equalsIgnoreCase("수정")) {
                WisdomEdit.edit(wisdomRegistry.getWisdomMap());
            }
        }
    }
}