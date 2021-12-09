package com.example.jolicitefx.Domain;

/**
 * Class representing a scene which can hold different spectacles
 */
public class Scene {
    private static int id_incrementor = 0;
    private final int id;
    private final int capacity;

    public Scene(int capacity) {
        this.capacity = capacity;
        this.id = id_incrementor++;
    }

    /**
     *
     * @return The identifier of the scene
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return The maximum capacity of the scene
     */
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
