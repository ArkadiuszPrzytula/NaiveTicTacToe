package com.pl.arek.tgame.model;

public enum FieldState {
    EMPTY(" "),
    X("X"),
    O("O");

    private String symbol;

    FieldState(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
