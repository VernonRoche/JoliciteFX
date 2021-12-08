package com.example.jolicitefx.Domain;

public class Scene {
    private static int id_incrementor = 0;
    private int id;
    private final int capacity;

    public Scene(int capacity) {
        this.capacity = capacity;
        this.id = id_incrementor;
        id_incrementor++;
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
