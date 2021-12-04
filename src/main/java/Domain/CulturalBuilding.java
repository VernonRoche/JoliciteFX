package Domain;

import java.util.ArrayList;
import java.util.Iterator;

public class CulturalBuilding {
    private final String name;
    private ArrayList<Scene> scenes;

    public CulturalBuilding(String name, ArrayList<Scene> scenes){
        this.name=name;
        this.scenes=scenes;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Scene> getScenes() {
        return scenes;
    }

    public void addScene(Scene scene){
        this.scenes.add(scene);
    }

    public void removeScene(int id){
        scenes.removeIf(x -> x.getId() == id);
    }

}
