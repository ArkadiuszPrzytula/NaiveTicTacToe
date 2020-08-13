package com.pl.arek.tgame.model.player;

import com.pl.arek.tgame.model.BoardModel;
import com.pl.arek.tgame.model.FieldState;
import com.pl.arek.tgame.model.TTTPosition;

import java.util.Random;

public class TTTRandomPlayer implements TTTPlayer {
    private Random random = new Random();

    @Override
    public TTTPosition getMarkPosition(BoardModel board) {
//        return getRandomFrom1To9(board);
        return getRandomWithOneAllocation(board);
    }

    private TTTPosition getRandomWithOneAllocation(BoardModel board) {
        while (true) {
            int randomX = random.nextInt(3);
            int randomY = random.nextInt(3);
            FieldState fieldStatus = board.getFieldStatus(randomX, randomY);
            if (fieldStatus == FieldState.EMPTY) {
                return new TTTPosition(randomX, randomY);
            }
        }
    }

    private TTTPosition getRandomFrom1To9(BoardModel board) {
        while (true) {
            int value = random.nextInt(9) + 1;
            TTTPosition position = getPositionWithMagic(value);
            FieldState fieldStatus = board.getFieldStatus(position.getX(), position.getY());
            if (fieldStatus == FieldState.EMPTY) {
                return position;
            }
        }
    }
}
