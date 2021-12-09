package com.example.jolicitefx.Domain;

/**
 * Interface used in classes Concert and TheaterPiece for uniform behaviour
 */
public interface Spectacle {

    Day[] getDay();

    String getName();

    Time getTime();

    String getType();
}
