package Domain;

public interface Repository {
    void saveCulturalBuilding(CulturalBuilding culturalBuilding);

    CulturalBuilding loadCulturalBuilding(String name);
}
