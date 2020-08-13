package com.pl.arek.tgame.controller;

import com.pl.arek.tgame.TicTacToe;
import com.pl.arek.tgame.model.FieldState;
import com.pl.arek.tgame.model.TTTPosition;
import com.pl.arek.tgame.model.TTTResult;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

public class BoardController {
    private TicTacToe logic;

    @FXML
    private TextField field;

    @FXML
    private GridPane gridBoard;

    private Line line = new Line(0, 0, 10, 10);


    @FXML
    public void initialize() {
        logic = new TicTacToe(null);
        refreshInfoText();

        field.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("MOUSE_MOVED");
                printReport(event);
            }
        });

        field.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("MOUSE_PRESSED");
                printReport(event);
            }
        });

        field.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("MOUSE_RELEASED");
                printReport(event);
            }
        });
    }

    private void printReport(MouseEvent event) {
        System.out.println("x1 = " + event.getX());
        System.out.println("y1 = " + event.getY());
    }

    private void refreshInfoText() {
        TTTResult gameResult = logic.checkGameResult();
        switch (gameResult) {
            case PLAYER_X_WIN:
                field.setText("Player X wins!");
                disableBoard();
                break;
            case PLAYER_O_WIN:
                field.setText("Player O wins!");
                disableBoard();
                break;
            case DRAW:
                field.setText("Draw!");
                disableBoard();
                break;
            case PENDING:
                if (logic.isPlayerXTurn()) {
                    field.setText("Player X turn");
                } else {
                    field.setText("Player O turn");
                }
                break;
        }

    }

    private void disableBoard() {
        gridBoard.setDisable(true);
    }

    @FXML
    private void handleBoardClick(ActionEvent event) {
        System.out.println(event);
        Object source = event.getSource();
        if (source instanceof Button) {
            Button button = (Button) source;
            String id = button.getId();
            TTTPosition position = idToPosition(id);
            logic.putMark(position.getX(), position.getY());
            FieldState status = logic.getFieldStatus(position.getX(), position.getY());
            button.setText(status.name());
            button.setDisable(true);
            refreshInfoText();
        }
    }

    private TTTPosition idToPosition(String id) {
        String input = id.replace("button", "");
        String[] split = input.split("_");
        if (split.length == 2) {
            int x = Integer.valueOf(split[0]);
            int y = Integer.valueOf(split[1]);
            return new TTTPosition(x, y);
        }
        return new TTTPosition(-1, -1);
    }
}

