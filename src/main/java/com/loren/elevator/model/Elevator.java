package com.loren.elevator.model;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int floor;
    private List<Passenger> passengers;
    private status door;
    private int maxPeople;
    private int maxFloor;
    private enum status {
        OPEN,
        CLOSE,
        STOP,
        UPWARD,
        DOWNWARD
    }

    public Elevator(int maxFloor, int maxPeople) {
        floor = 1;
        door = status.CLOSE;
        passengers = new ArrayList<>();
        this.maxPeople = maxPeople;
        this.maxFloor = maxFloor;
    }

    public boolean enter(Passenger passenger) {
        if(passenger.getFloor() != floor) return false;
        if(door != status.OPEN) return false;
        if(passengers.size() >= maxPeople) return false;

        passengers.add(passenger);

        return true;
    }

    public boolean exit(Passenger passenger) {
        if(passenger.getFloor() != floor) return false;
        if(door != status.OPEN) return false;

        passengers.remove(passenger);

        return true;
    }

    public boolean up() {
        if(door == status.OPEN || door == status.DOWNWARD) return false;
        if(floor + 1 >= maxFloor) return true;
        passengers.forEach(Passenger::up);
        floor++;
        door = status.UPWARD;
        return true;
    }

    public boolean down() {
        if(door == status.OPEN || door == status.UPWARD) return false;
        if(floor == 0) return true;
        passengers.forEach(Passenger::down);
        floor--;
        door = status.DOWNWARD;
        return true;
    }

    public boolean open() {
        if(door == status.UPWARD || door == status.DOWNWARD) return false;
        door = status.OPEN;
        return true;
    }

    public boolean close() {
        if(door == status.UPWARD || door == status.DOWNWARD) return false;
        door = status.CLOSE;
        return true;
    }

    public boolean stop() {
        if(door == status.OPEN || door == status.CLOSE) return true;
        door = status.STOP;
        return true;
    }

    public int getFloor() {
        return floor;
    }

    public Passenger hasPassenger(int id) {
        for(Passenger p : passengers) {
            if(p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    public void showStat(int id) {
        System.out.print("\t\t[" + id + ":");
        for(int i = 0; i < passengers.size(); i++) {
            System.out.print(passengers.get(i).getId());
            if(i != passengers.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]\t");
    }

    /////////////////////////////// forTest
    public void setFloor(int f) {
        floor = f;
    }

    public int getPassengerSize() {
        return passengers.size();
    }
}
