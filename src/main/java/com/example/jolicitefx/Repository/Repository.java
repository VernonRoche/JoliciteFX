package com.example.jolicitefx.Repository;

import com.example.jolicitefx.Domain.CulturalBuilding;
import com.example.jolicitefx.Domain.Event;
import com.example.jolicitefx.Domain.Schedule;
import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Repository implements com.example.jolicitefx.Domain.Repository {

    public static Set<CulturalBuilding> memory = new HashSet<>();

    @Override
    public void saveCulturalBuilding(CulturalBuilding culturalBuilding) {
        memory.add(culturalBuilding);
    }

    @Override
    public CulturalBuilding loadCulturalBuilding(String name) {
        for(CulturalBuilding culturalBuilding : memory){
            if(culturalBuilding.getName().equals(name))
                return culturalBuilding;

        }
        return null;
    }

    public void saveInFile(CulturalBuilding culturalBuilding){
        try {

            File file = new File("save.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("CulturalBuilding { \n");

            for(Pair<Integer, ArrayList<Pair<Schedule, Event>>> reserved_schedule : culturalBuilding.getReserved_schedules()){
                int week = reserved_schedule.getKey();
                for(Pair<Schedule,Event> schedule :reserved_schedule.getValue()){
                    fileWriter.write("  Scene: " + schedule.getKey().getScene_id());
                    fileWriter.write("  Event: " + schedule.getValue().getSpectacle().getName());
                    fileWriter.write("  Week: " + week);
                    fileWriter.write("  Day: " + schedule.getKey().getDay().name());
                    fileWriter.write("  Time: " + schedule.getKey().getTime().toString());
                    fileWriter.write("  Capacity: " + schedule.getValue().getCapacity_needed());
                }
                fileWriter.write("\n");
            }
            fileWriter.write("}\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
