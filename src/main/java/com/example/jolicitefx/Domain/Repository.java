package com.example.jolicitefx.Domain;

public interface Repository {
    void saveCulturalBuilding(CulturalBuilding culturalBuilding);

    CulturalBuilding loadCulturalBuilding(String name);
}
