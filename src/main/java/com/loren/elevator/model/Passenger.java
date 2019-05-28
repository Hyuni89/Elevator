package com.loren.elevator.model;

import java.util.Random;

import lombok.Data;

@Data
public class Passenger {
    private int targetFloor;
    private int sourceFloor;
    private int floor;
    private int id;

    public static int PASSENGER_ID = 0;

    public Passenger(int limit) {
        Random r = new Random();

        targetFloor = r.nextInt(limit);
        do {
            sourceFloor = r.nextInt(limit);
        } while(targetFloor == sourceFloor);

        ++targetFloor;
        ++sourceFloor;
        floor = sourceFloor;

        this.id = PASSENGER_ID++;
    }

    public boolean up() {
        return ++floor == targetFloor;
    }

    public boolean down() {
        return --floor == targetFloor;
    }
}
