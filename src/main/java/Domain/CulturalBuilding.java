package Domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

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

    public void generateWeeklyProgram(ArrayList<String[]> string_events){
        ArrayList<Event> events = new ArrayList<>();
        for (String[] event_string:string_events){
            events.add(new Event(event_string));
        }

        // Iterate over concerts to give them priority
        Iterator<Event> itr = events.iterator();
        while (itr.hasNext()) {
            Event event = itr.next();
            if (Objects.equals(event.getSpectacle().getType(), "Concert")){
                for (Scene scene : this.scenes){
                    if (scene.hasFreeWeekend()){
                        scene.addEvent(event);
                    }
                }
                itr.remove();
            }

        }
    }

}
