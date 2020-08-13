package com.pl.arek.tgame.model.player;

import com.pl.arek.tgame.TicTacToe;
import com.pl.arek.tgame.model.BoardModel;
import com.pl.arek.tgame.model.TTTPosition;

public interface TTTPlayer {
    TTTPosition getMarkPosition(BoardModel board);

    /**
     * Get position based on parameter
     * @param position value from 1 to 9
     * @return wanted position on board
     */
    default TTTPosition getPositionWithMagic(int position) {
        int x = (position - 1) % TicTacToe.BOARD_SIZE;
        int y = (position - 1) / TicTacToe.BOARD_SIZE;
        return new TTTPosition(x, y);
    }
}
