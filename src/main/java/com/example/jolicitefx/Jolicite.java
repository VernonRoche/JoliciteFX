package com.example.jolicitefx;

import Domain.*;

import java.util.Arrays;

public class Jolicite{

    public static void main(String[] args) {
        int[] concert_date = {19,12,2021};
        int[] concert_schedule = {15, 30};
        Concert concert = new Concert(concert_date, "White Stripes", concert_schedule);
        Event test_event = new Event(concert, 50);
        Spectacle spectacle = test_event.getSpectacle();
        System.out.println(Arrays.deepToString(spectacle.getDate()));
    }
}