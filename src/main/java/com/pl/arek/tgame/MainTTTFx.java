package com.pl.arek.tgame;


import com.pl.arek.tgame.controller.BoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainTTTFx extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("/view/TTTBoard.fxml");
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(new BoardController());
        Parent root = loader.load();
        Object controller = loader.getController();
        stage.setTitle("Tic Tac Toe");
        stage.setScene(new Scene(root, stage.getWidth(),
                stage.getHeight()));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
