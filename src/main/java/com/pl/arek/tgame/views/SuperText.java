package com.pl.arek.tgame.views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class SuperText extends TextField {
    private final Canvas canvas;

    public SuperText() {
        canvas = new Canvas(getWidth(), getHeight());
        getChildren().add(canvas);
        widthProperty().addListener(e -> canvas.setWidth(getWidth()));
        heightProperty().addListener(e -> canvas.setHeight(getHeight()));
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setFill(Color.AQUA);
        gc.fillRect(10, 10, getWidth() - 20, getHeight() - 20);

    }
}
