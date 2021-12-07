package Domain;

import javafx.util.Pair;

import java.util.*;

public class CulturalBuilding { //ArrayList<Pair<Integer,>>
    private final String name;
    private ArrayList<Pair<Integer,ArrayList<Schedule>>> available_schedules = new ArrayList<>();
    private ArrayList<Pair<Integer,ArrayList<Schedule>>> reserved_schedules = new ArrayList<>();
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Scene reserved_scene;

    public CulturalBuilding(String name, ArrayList<Scene> scenes, ArrayList<Pair<Integer,ArrayList<Schedule>>> available_schedules, ArrayList<Pair<Integer,ArrayList<Schedule>>> reserved_schedules){
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

    public void addScene(String[] scene){
        //quelque chose
    }

    public void removeScene(int id){
        scenes.removeIf(x -> x.getId() == id);
    }

    public void setUpReservedScene(Scene scene){
        this.reserved_scene=scene;
    }

    public void addWeek(int week_number){
        if (week_number<1 || week_number>53){
            throw new IllegalArgumentException("Wrong week number input");
        }
        available_schedules.add(new Pair<>(week_number, new ArrayList<>()));
        reserved_schedules.add(new Pair<>(week_number, new ArrayList<>()));
    }

    public void programEvent(Event event, int week){
        // Iterate through all weeks
        Iterator<Pair<Integer, ArrayList<Schedule>>> week_iterator = available_schedules.iterator();
        while (week_iterator.hasNext()){
            Pair<Integer, ArrayList<Schedule>> week_schedules =week_iterator.next();
            int week_key = week_schedules.getKey();

            // Check if week exists, else create it and proceed to that week's program
            if (week_key==week) {
                ArrayList<Schedule> week_available_schedules = week_schedules.getValue();
                Iterator<Schedule> iterator = week_available_schedules.iterator();

                // Iterate through the week's available schedules
                while (iterator.hasNext()) {
                    Schedule available_schedule = iterator.next();
                    int scene_id = available_schedule.getScene_id();

                    // Check if we there is an available slot for the event
                    if (available_schedule.getDay() == event.getSpectacle().getDay()[0]) {
                        if (available_schedule.getTime().isTimeWithinBoundaries(event.getSpectacle().getTime())) {

                            // It's good, we can add the event to our reserved schedules
                            for (Pair<Integer, ArrayList<Schedule>> reserved_week_schedule: reserved_schedules){
                                if (reserved_week_schedule.getKey() == week){
                                    ArrayList<Schedule> new_reserved_week_schedule = reserved_week_schedule.getValue();
                                    new_reserved_week_schedule.add(available_schedule);
                                    reserved_schedules.add(new Pair<>(week, new_reserved_week_schedule));
                                }
                            }

                            iterator.remove();

                            // After reserving the schedule, add the remaining free time to available schedules
                            if (!available_schedule.getTime().isStartTimeEqual(event.getSpectacle().getTime())) {
                                int[] start_time = available_schedule.getTime().getStart_time();
                                int[] end_time = event.getSpectacle().getTime().getStart_time();
                                Schedule schedule = new Schedule(scene_id, available_schedule.getDay(), new Time(start_time, end_time));
                                week_available_schedules.add(schedule);
                                available_schedules.add(new Pair<>(week, week_available_schedules));
                            }
                            if (!available_schedule.getTime().isEndTimeEqual(event.getSpectacle().getTime())) {
                                int[] start_time = event.getSpectacle().getTime().getEnd_time();
                                int[] end_time = available_schedule.getTime().getEnd_time();
                                Schedule schedule = new Schedule(scene_id, available_schedule.getDay(), new Time(start_time, end_time));
                                week_available_schedules.add(schedule);
                                available_schedules.add(new Pair<>(week, week_available_schedules));
                            }
                            break;
                        }
                    }
                }
            }
        }

    }

    public void generateWeeklyProgram(ArrayList<String[]> string_events, int week){
        for (String[] string_event : string_events){
            Event new_event = new Event(string_event);
            programEvent(new_event, week);
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
