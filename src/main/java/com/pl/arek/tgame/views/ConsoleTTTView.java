package com.pl.arek.tgame.views;

import com.pl.arek.tgame.TicTacToe;
import com.pl.arek.tgame.model.BoardModel;
import com.pl.arek.tgame.model.FieldState;

public class ConsoleTTTView implements TicTacToeView {
    @Override
    public void printBoard(BoardModel board) {
        for (int i = 0; i < TicTacToe.BOARD_SIZE; i++) {
            for (int j = 0; j < TicTacToe.BOARD_SIZE; j++) {
                FieldState fieldStatus = board.getFieldStatus(j, i);
                System.out.print(fieldStatus.getSymbol());

                if (j < TicTacToe.BOARD_SIZE - 1)
                    System.out.print("|");
            }

            if (i < TicTacToe.BOARD_SIZE - 1)
                System.out.println("\n-+-+-");
        }
        System.out.println();
    }
}

