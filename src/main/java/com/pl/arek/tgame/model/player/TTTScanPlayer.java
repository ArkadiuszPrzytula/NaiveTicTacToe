package com.pl.arek.tgame.model.player;

import com.pl.arek.tgame.model.BoardModel;
import com.pl.arek.tgame.model.TTTPosition;

import java.util.Scanner;

public class TTTScanPlayer implements TTTPlayer {

    @Override
    public TTTPosition getMarkPosition(BoardModel board) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Put number 1 - 9");
            try {
                String input = sc.next();
                int position = Integer.valueOf(input);

                if (position > 0 && position <= 9)
                    return getPositionWithMagic(position);
            } catch (Exception ignore) {
            }
            System.out.println("Źle!");
        } while (true);
    }

    private TTTPosition getPositionWithSwitch(int position) {
        switch (position) {
            default:
            case 1:
                return new TTTPosition(0, 0);
            case 2:
                return new TTTPosition(1, 0);
            case 3:
                return new TTTPosition(2, 0);
            case 4:
                return new TTTPosition(0, 1);
            case 5:
                return new TTTPosition(1, 1);
            case 6:
                return new TTTPosition(2, 1);
            case 7:
                return new TTTPosition(0, 2);
            case 8:
                return new TTTPosition(1, 2);
            case 9:
                return new TTTPosition(2, 2);
        }
    }

}

