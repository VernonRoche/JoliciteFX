package com.example.jolicitefx.Domain;

/**
 * Main interface used for a repository
 */
public interface Repository {
    void saveCulturalBuilding(CulturalBuilding culturalBuilding);

    CulturalBuilding loadCulturalBuilding(String name);
}
