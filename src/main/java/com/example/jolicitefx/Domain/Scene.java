package com.example.jolicitefx.Domain;

import java.util.ArrayList;

public class Scene {
    private static int id = 0;
    private final int capacity;

    public Scene(int capacity) {
        this.capacity = capacity;
        id++;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public String toString() {
        return "Scene{" +
                "id " + id +
                "capacity=" + capacity +
                '}';
    }
}
