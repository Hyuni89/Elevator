package com.loren.elevator.model;

import com.loren.elevator.connection.CallWrap;
import com.loren.elevator.connection.CommandWrap;
import com.loren.elevator.connection.ElevatorWrap;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Component
public class Building {
    private int height;
    private ArrayList[] passengers;
    private List<Elevator> elevators;
    private int total;
    private int hitCount;
    private int timestamp;

    public void init(int height, int cntElevator, int maxPeople, int total) {
        this.height = height;
        this.total = total;
        timestamp = 0;
        hitCount = 0;
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

        for(Passenger p : pass) {
            if(p.getTargetFloor() != floor) {
                passengers[floor].add(p);
            } else {
                hitCount++;
            }
        }

        return true;
    }

    public boolean call() {
        Random r = new Random();
        int cnt = r.nextInt(5) + 1;
        for(int i = 0; i < cnt; i++) {
            if(total <= Passenger.PASSENGER_ID) {
                break;
            }

            Passenger p = new Passenger(height, timestamp);
            passengers[p.getFloor()].add(p);
        }

        return true;
    }

    public List<ElevatorWrap> getElevatorWrapStatus() {
        List<ElevatorWrap> elevatorWraps = new ArrayList<>();

        for(int id = 0; id < elevators.size(); id++) {
            ElevatorWrap ew = new ElevatorWrap();
            ew.setId(id);
            ew.setFloor(elevators.get(id).getFloor());
            ew.setPassengers(elevators.get(id).getPassengersId());
            ew.setStatus(elevators.get(id).getStatusString());
            elevatorWraps.add(ew);
        }

        return elevatorWraps;
    }

    public List<CallWrap> getCallWrapStatus() {
        List<CallWrap> callWraps = new ArrayList<>();

        for(int i = 0; i < height; i++) {
            for(Object p : passengers[i]) {
                CallWrap cw = new CallWrap();
                Passenger pass = (Passenger)p;
                cw.setEnd(pass.getTargetFloor());
                cw.setStart(pass.getSourceFloor());
                cw.setId(pass.getId());
                cw.setTimestamp(pass.getTimestamp());
                callWraps.add(cw);
            }
        }
        Collections.sort(callWraps, new Comparator<CallWrap>() {
            @Override
            public int compare(CallWrap o1, CallWrap o2) {
                return o1.getId() - o2.getId();
            }
        });

        return callWraps;
    }

    public int getTimestamp() {
        return timestamp++;
    }

    public boolean getIsEnd() {
        return hitCount == total;
    }

    public boolean doCommand(CommandWrap c) {
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
        System.out.print(String.format("htiCount[%d], timestamp[%d]\n", hitCount, timestamp));
        System.out.println("=============================");
    }
}
