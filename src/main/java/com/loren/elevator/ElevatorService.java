package com.loren.elevator;

import com.loren.elevator.command.wrapper.CallWrap;
import com.loren.elevator.command.wrapper.CommandWrap;
import com.loren.elevator.command.wrapper.ElevatorWrap;
import com.loren.elevator.model.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElevatorService {
    @Autowired
    private Building building;

    private boolean running;

    public ElevatorService() {
        running = false;
    }

    public boolean start(int height, int cnt) {
        int totalCallPeople = -1;
        int elevatorMaxPeople = 8;

        switch(height) {
            case 5: totalCallPeople = 50; break;
            case 10: totalCallPeople = 100; break;
            case 20: totalCallPeople = 200; break;
            default: return false;
        }

        if(cnt <= 0 || cnt > 4) return false;

        building.init(height, cnt, elevatorMaxPeople, totalCallPeople);
        running = true;

        return true;
    }

    public void call() {
        building.call();
        building.showStat();
    }

    public void action(List<CommandWrap> req) throws Exception {
        boolean res = building.doCommand(req);
        if(!res) {
            building.doRollback();
            throw new Exception();
        }
        building.showStat();
    }

    public boolean getIsEnd() {
        boolean ret = building.getIsEnd();
        if(ret) {
            running = false;
            System.out.println("===== Congratulation! You've Done It! =====");
        }
        return ret;
    }

    public boolean isRunning() {
        return running;
    }

    public int getTimestamp() {
        return building.getTimestamp();
    }

    public List<ElevatorWrap> getElevatorWrapStatus() {
        return building.getElevatorWrapStatus();
    }

    public List<CallWrap> getCallWrapStatus() {
        return building.getCallWrapStatus();
    }
}
