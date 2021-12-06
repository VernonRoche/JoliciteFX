package Domain;

import javafx.util.Pair;

import java.util.*;

public class CulturalBuilding {
    private final String name;
    private ArrayList<Schedule> available_schedules = new ArrayList<>();
    private ArrayList<Schedule> reserved_schedules = new ArrayList<>();
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Scene reserved_scene;

    public CulturalBuilding(String name, ArrayList<Scene> scenes, ArrayList<Schedule> available_schedules, ArrayList<Schedule> reserved_schedules){
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
        Iterator<Schedule> itr = available_schedules.iterator();
        while (itr.hasNext()){
            Schedule available_schedule = itr.next();
            int scene_id = available_schedule.getScene_id();
            if (available_schedule.getDay() == event.getSpectacle().getDay()[0]){
                if (available_schedule.getTime().isTimeWithinBoundaries(event.getSpectacle().getTime())){
                    reserved_schedules.add(available_schedule);
                    itr.remove();
                    if(!available_schedule.getTime().isStartTimeEqual(event.getSpectacle().getTime())){
                        int[] start_time = available_schedule.getTime().getStart_time();
                        int[] end_time = event.getSpectacle().getTime().getStart_time();
                        Schedule schedule = new Schedule(scene_id,available_schedule.getDay(),new Time(start_time,end_time));
                        available_schedules.add(schedule);
                    }
                    if(!available_schedule.getTime().isEndTimeEqual(event.getSpectacle().getTime())){
                        int[] start_time = event.getSpectacle().getTime().getEnd_time();
                        int[] end_time = available_schedule.getTime().getEnd_time();
                        Schedule schedule = new Schedule(scene_id,available_schedule.getDay(),new Time(start_time,end_time));
                        available_schedules.add(schedule);
                    }
                    break;
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

    @Override
    public String toString() {
        return "CulturalBuilding{" +
                "name='" + name + '\'' +
                ", available_schedules=" + available_schedules +
                ", reserved_schedules=" + reserved_schedules +
                ", scenes=" + scenes +
                ", reserved_scene=" + reserved_scene +
                '}';
    }
}
