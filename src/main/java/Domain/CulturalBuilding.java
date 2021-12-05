package Domain;

import javafx.util.Pair;

import java.util.*;

public class CulturalBuilding {
    private final String name;
    private ArrayList<Pair<Integer, Schedule>> available_schedules = new ArrayList<>();
    private ArrayList<Pair<Integer, Schedule>> reserved_schedules = new ArrayList<>();
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Scene reserved_scene;

    public CulturalBuilding(String name, ArrayList<Scene> scenes, ArrayList<Pair<Integer, Schedule>> available_schedules, ArrayList<Pair<Integer, Schedule>> reserved_schedules){
        this.name=name;
        this.scenes.addAll(scenes);
        this.reserved_schedules.addAll(reserved_schedules);
        this.available_schedules.addAll(available_schedules);
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

    public void setUpReservedScene(Scene scene){
        this.reserved_scene=scene;
    }

    public void programEvent(Event event){
        Iterator<Pair<Integer, Schedule>> itr = available_schedules.iterator();
        while (itr.hasNext()){
            Pair<Integer, Schedule> available_schedule = itr.next();
            int scene_id = available_schedule.getKey();
            Schedule schedule = available_schedule.getValue();
            if (schedule.getDay() == event.getSpectacle().getDay()[0]){
                if (schedule.getTime().isTimeWithinBoundaries(event.getSpectacle().getTime())){
                    Pair<Integer, Schedule> reserved_schedule = new Pair<>(scene_id, schedule);
                    reserved_schedules.add(reserved_schedule);
                    available_schedules.remove(available_schedule);
                }
            }
        }

    }

    public void generateWeeklyProgram(ArrayList<String[]> string_events){
        for (String[] string_event : string_events){
            Event new_event = new Event(string_event);
            programEvent(new_event);
        }
    }

}
