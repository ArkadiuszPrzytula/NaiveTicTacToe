package com.pl.arek.tgame;

import com.pl.arek.tgame.model.FieldState;
import com.pl.arek.tgame.model.TTTResult;
import com.pl.arek.tgame.views.TicTacToeView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.pl.arek.tgame.TicTacToe.BOARD_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;


class TicTacToeTest {
    private TicTacToe game;
    private TicTacToeView view;

    @BeforeEach
    public void setup() {
        // given
        //todo zmien na mock
        view = mock(TicTacToeView.class);
//        view = new ConsoleTTTView();
        game = new TicTacToe(view);
        System.out.println("reset");
    }

    @Test
    public void atBeginningFieldsAreEmpty() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.println("Sprawdzam pole " + i + "x1" + j);
                // when
                FieldState state = game.getFieldStatus(i, j);

                // then
                assertEquals(FieldState.EMPTY, state);
            }
        }
    }

    @Test
    public void firstActionOnBoardIsX() {
        // given

        // when
        game.putMark(0, 0);

        // then
        FieldState status = game.getFieldStatus(0, 0);
        assertEquals(FieldState.X, status);
    }

    @Test
    public void secondActionOnBoardIsO() {
        // given

        // when
        game.putMark(0, 0);
        game.putMark(0, 1);

        // then
        FieldState status = game.getFieldStatus(0, 1);
        assertEquals(FieldState.O, status);
    }

    @Test
    public void gameShouldThrowWhenFieldIsTaken() {
        // given
        game.putMark(0, 0);

        // when then
        assertThrows(FieldTakenException.class,
                () -> game.putMark(0, 0));
    }

    @Test
    public void putMarkTooMuchOnLeftShouldThrow() {
        // when then
        assertThrows(IllegalArgumentException.class,
                () -> game.putMark(-1, 0));
    }

    @Test
    public void putMarkTooMuchOnTopShouldThrow() {
        // when then
        assertThrows(IllegalArgumentException.class,
                () -> game.putMark(0, -1));
    }

    @Test
    public void putMarkTooMuchOnRightShouldThrow() {
        // when then
        assertThrows(IllegalArgumentException.class,
                () -> game.putMark(3, 0));
    }

    @Test
    public void putMarkTooMuchOnBottomShouldThrow() {
        // when then
        assertThrows(IllegalArgumentException.class,
                () -> game.putMark(0, 3));
    }

    @Test
    public void dummyBoardTest() {
        game.putMark(0, 0);
        game.putMark(1, 1);
        game.putMark(2, 2);

        game.refreshView();
    }

    @Test
    public void playerXWinsHorizontallyTop() {
        // given
        game.putMark(0, 0);
        game.putMark(1, 0);
        game.putMark(0, 1);
        game.putMark(2, 0);
        game.putMark(0, 2);

        game.refreshView();
        // when
        TTTResult result = game.checkGameResult();

        // then
        assertEquals(TTTResult.PLAYER_X_WIN, result);
    }

    @Test
    public void playerXWinsVertically() {
        // given
        game.putMark(0, 1);
        game.putMark(0, 0);
        game.putMark(1, 1);
        game.putMark(1, 0);
        game.putMark(2, 1);

        game.refreshView();
        // when
        TTTResult result = game.checkGameResult();

        // then
        assertEquals(TTTResult.PLAYER_X_WIN, result);
    }

    @Test
    public void playerXWinsDiagonallyFromLeft() {
        // given
        game.putMark(0, 0);
        game.putMark(0, 1);
        game.putMark(1, 1);
        game.putMark(0, 2);
        game.putMark(2, 2);

        game.refreshView();
        // when
        TTTResult result = game.checkGameResult();

        // then
        assertEquals(TTTResult.PLAYER_X_WIN, result);
    }

    @Test
    public void playerXWinsDiagonallyFromRight() {
        // given
        game.putMark(2, 0);
        game.putMark(1, 0);
        game.putMark(1, 1);
        game.putMark(0, 0);
        game.putMark(0, 2);

        game.refreshView();
        // when
        TTTResult result = game.checkGameResult();

        // then
        assertEquals(TTTResult.PLAYER_X_WIN, result);
    }

    @Test
    public void playerOWinsHorizontallyBottom() {
        // given
        game.putMark(0, 0);
        game.putMark(0, 2);
        game.putMark(1, 0);
        game.putMark(1, 2);
        game.putMark(1, 1);
        game.putMark(2, 2);

        game.refreshView();
        // when
        TTTResult result = game.checkGameResult();

        // then
        assertEquals(TTTResult.PLAYER_O_WIN, result);
    }

    @Test
    public void gameResultIsPendingAtStart() {
        // when
        TTTResult result = game.checkGameResult();

        // then
        assertEquals(TTTResult.PENDING, result);
    }

    @Test
    public void gameResultIsDraw() {
        // given
        game.putMark(1, 1);
        game.putMark(0, 0);
        game.putMark(0, 1);
        game.putMark(0, 2);
        game.putMark(1, 0);
        game.putMark(1, 2);
        game.putMark(2, 0);
        game.putMark(2, 1);
        game.putMark(2, 2);

        game.refreshView();

        // when
        TTTResult result = game.checkGameResult();

        // then
        assertEquals(TTTResult.DRAW, result);
    }



}