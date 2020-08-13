package com.pl.arek.tgame;

import com.pl.arek.tgame.model.TTTPosition;
import com.pl.arek.tgame.model.TTTResult;
import com.pl.arek.tgame.model.player.TTTPlayer;
import com.pl.arek.tgame.model.player.TTTRandomPlayer;
import com.pl.arek.tgame.model.player.TTTScanPlayer;
import com.pl.arek.tgame.views.ConsoleTTTView;

public class MainTTTGame {
    public static void main(String[] args) {
        TTTPlayer playerX = new TTTScanPlayer();
        TTTPlayer playerO = new TTTRandomPlayer();
        TicTacToe game = new TicTacToe(new ConsoleTTTView());

        TTTPosition position;
        TTTResult result;
        do {
            if (game.isPlayerXTurn()) {
                System.out.println("turn X player");
                position = playerX.getMarkPosition(game);
            } else {
                System.out.println("Turn O player");
                position = playerO.getMarkPosition(game);
            }
            try {
                game.putMark(position.getX(), position.getY());
                game.refreshView();
            } catch (FieldTakenException field) {
                System.out.println("This fild is taken!");
            }
            result = game.checkGameResult();
        } while (result == TTTResult.PENDING);

        switch (result) {
            case PLAYER_X_WIN:
                System.out.println("Player X win!");
                break;
            case PLAYER_O_WIN:
                System.out.println("Player O win!");
                break;
            case DRAW:
                System.out.println("Draw!");
                break;
        }
    }
}
