package com.loren.elevator.model;

import com.loren.elevator.connection.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Building {
    private int height;
    private ArrayList[] passengers;
    private List<Elevator> elevators;

    public Building(int height, int cntElevator, int maxPeople) {
        this.height = height;
        passengers = new ArrayList[height];
        for(int i = 0; i < height; i++) {
            passengers[i] = new ArrayList<Passenger>();
        }
        elevators = new ArrayList<>();
        for(int i = 0; i < cntElevator; i++) {
            elevators.add(new Elevator(height, maxPeople));
        }
    }

    public boolean open(int index) {
        return elevators.get(index).open();
    }

    public boolean close(int index) {
        return elevators.get(index).close();
    }

    public boolean up(int index) {
        return elevators.get(index).up();
    }

    public boolean down(int index) {
        return elevators.get(index).down();
    }

    public boolean stop(int index) {
        return elevators.get(index).stop();
    }

    public boolean enter(int index, List<Integer> ids) {
        int floor = elevators.get(index).getFloor();
        List<Passenger> pass = new ArrayList<>();

        for(Integer id : ids) {
            for(Object p : passengers[floor]) {
                int pid = ((Passenger)p).getId();
                if(pid == id) {
                    pass.add((Passenger)p);
                    break;
                }
            }
        }

        if(pass.size() != ids.size()) {
            return false;
        }

        if(!elevators.get(index).enter(pass)) {
            return false;
        }
        for(Passenger p : pass) {
            passengers[floor].remove(p);
        }

        return true;
    }

    public boolean exit(int index, List<Integer> ids) {
        int floor = elevators.get(index).getFloor();
        List<Passenger> pass = new ArrayList<>();

        for(Integer id : ids) {
            Passenger p = elevators.get(index).hasPassenger(id);

            if(p == null) return false;

            pass.add(p);
        }

        if(!elevators.get(index).exit(pass)) {
            return false;
        }
        passengers[floor].addAll(pass);

        return true;
    }

    public void call() {
        Random r = new Random();
        int cnt = r.nextInt(5) + 1;
        for(int i = 0; i < cnt; i++) {
            Passenger p = new Passenger(height);
            passengers[p.getFloor()].add(p);
        }
    }

    public boolean doCommand(Command c) {
        int elevatorId = c.getElevatorId();
        String command = c.getCommand();
        List<Integer> callIds = c.getCallIds();
        boolean ret = true;

        switch(command) {
            case "OPEN":
                ret = open(elevatorId);
                break;
            case "CLOSE":
                ret = close(elevatorId);
                break;
            case "UP":
                ret = up(elevatorId);
                break;
            case "DOWN":
                ret = down(elevatorId);
                break;
            case "STOP":
                ret = stop(elevatorId);
                break;
            case "ENTER":
                ret = enter(elevatorId, callIds);
                break;
            case "EXIT":
                ret = exit(elevatorId, callIds);
                break;
            default:
        }

        return ret;
    }

    public void showStat() {
        System.out.println("=============================");
        for(int i = 1; i <= height; i++) {
            System.out.print(i + "[");
            for(int j = 0; j < passengers[i - 1].size(); j++) {
                System.out.print(((Passenger)passengers[i - 1].get(j)).getId());
                if(j != passengers[i - 1].size() - 1) {
                    System.out.print("][");
                }
            }
            System.out.print("]");
            for(int j = 0; j < elevators.size(); j++) {
                if(elevators.get(j).getFloor() == i - 1) {
                    elevators.get(j).showStat(j);
                }
            }
            System.out.println();
        }
        System.out.println("=============================");
    }
}
