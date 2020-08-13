package com.pl.arek.tgame;

import com.pl.arek.tgame.model.BoardModel;
import com.pl.arek.tgame.model.FieldState;
import com.pl.arek.tgame.model.TTTResult;
import com.pl.arek.tgame.views.TicTacToeView;


public class TicTacToe implements BoardModel {

    public final static int BOARD_SIZE = 3;

    private FieldState[][] board;
    private boolean isPlayerXTurn = true;
    private TicTacToeView view;

    public TicTacToe(TicTacToeView view) {
        this.view = view;
        board = new FieldState[BOARD_SIZE][BOARD_SIZE];
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = FieldState.EMPTY;
            }
        }
    }


    public FieldState getFieldStatus(int x, int y) {
        return board[y][x];
    }

    public void putMark(int x, int y) {
        if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
            throw new IllegalArgumentException();
        }
        if (getFieldStatus(x, y) != FieldState.EMPTY) {
            throw new FieldTakenException(x, y);
        }

        if (isPlayerXTurn) {
            board[y][x] = FieldState.X;
        } else {
            board[y][x] = FieldState.O;
        }
        isPlayerXTurn = !isPlayerXTurn;

    }

    public void refreshView() {
        view.printBoard(this);
    }

    public TTTResult checkGameResult() {

        if (isDraw()) {
            return TTTResult.DRAW;
        }
        if (isPlayerXWin()) {
            return TTTResult.PLAYER_X_WIN;
        }
        if (isPlayerOWin()) {
            return TTTResult.PLAYER_O_WIN;
        }
        return TTTResult.PENDING;
    }

    private boolean isDraw() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (getFieldStatus(i, j) == FieldState.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPlayerXWin() {
        FieldState checkedField = FieldState.X;
        return isPlayerWinner(checkedField);
    }

    private boolean isPlayerOWin() {
        FieldState checkedField = FieldState.O;
        return isPlayerWinner(checkedField);
    }

    private boolean isPlayerWinner(FieldState checkedField) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            //check horizontally
            if (getFieldStatus(i, 0) == checkedField &&
                    getFieldStatus(i, 0) == getFieldStatus(i, 1) &&
                    getFieldStatus(i, 1) == getFieldStatus(i, 2)) {
                return true;
            }
            //check vertically
            if (getFieldStatus(0, i) == checkedField &&
                    getFieldStatus(0, i) == getFieldStatus(1, i) &&
                    getFieldStatus(1, i) == getFieldStatus(2, i)) {
                return true;
            }
        }

        //check cross right to left
        if (getFieldStatus(0, 0) == checkedField &&
                getFieldStatus(1, 1) == checkedField &&
                getFieldStatus(2, 2) == checkedField) {
            return true;
        }
        //check cross left to right
        return getFieldStatus(2, 0) == checkedField &&
                getFieldStatus(1, 1) == checkedField &&
                getFieldStatus(0, 2) == checkedField;
    }

    public boolean isPlayerXTurn() {
        return isPlayerXTurn;
    }

}
