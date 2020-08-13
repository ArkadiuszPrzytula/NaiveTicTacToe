package com.pl.arek.tgame;

public class FieldTakenException extends RuntimeException {
    public FieldTakenException() {}

    public FieldTakenException(String message) {
        super(message);
    }

    public FieldTakenException(int x, int y) {
        this(String.format("%dx%d is taken!", x, y));
    }
}
