package com.loren.elevator;

import com.loren.elevator.connection.ActionRequest;
import com.loren.elevator.connection.ActionResponse;
import com.loren.elevator.connection.CallResponse;
import com.loren.elevator.connection.CommandWrap;
import com.loren.elevator.connection.CommonResponse;
import com.loren.elevator.connection.StartRequest;
import com.loren.elevator.model.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.loren.elevator.connection.CommonResponse.STATUS_NOTOK;
import static com.loren.elevator.connection.CommonResponse.STATUS_OK;

@RestController
public class ElevatorController {

    private boolean doing;

    @Autowired
    private Building building;

    @Qualifier("commonResponse")
    @Autowired
    private CommonResponse ret;

    @Qualifier("actionResponse")
    @Autowired
    private ActionResponse aret;

    @Autowired
    private CallResponse cret;

    public ElevatorController() {
        doing = false;
    }

    @PostMapping("/start")
    public @ResponseBody CommonResponse start(@RequestBody StartRequest request) {
        if(doing) {
            ret.setStatus(STATUS_NOTOK);
            return ret;
        }
        doing = true;

        int elevatorMaxPeople = 8;
        int buildingHeight = 5;
        int totalCallPeople = -1;
        int elevatorCnt = request.getElevatorCnt();

        switch(request.getBuildingHeight()) {
            case 5: buildingHeight = 5; totalCallPeople = 50; break;
            case 10: buildingHeight = 10; totalCallPeople = 100; break;
            case 20: buildingHeight = 20; totalCallPeople = 200; break;
            default: buildingHeight = -1;
        }

        if(elevatorCnt <= 0 || elevatorCnt > 5) {
            elevatorCnt = -1;
        }

        if(elevatorCnt == -1 || buildingHeight == -1) {
            ret.setStatus(STATUS_NOTOK);
        } else {
            building.init(buildingHeight, elevatorCnt, elevatorMaxPeople, totalCallPeople);
            ret.setStatus(STATUS_OK);
        }

        return ret;
    }

    @GetMapping("/call")
    public @ResponseBody CommonResponse call() {
        if(!doing) {
            ret.setStatus(STATUS_NOTOK);
            return ret;
        }

        building.call();
        building.showStat();

        cret.setElevators(building.getElevatorWrapStatus());
        cret.setCalls(building.getCallWrapStatus());
        cret.setTimestamp(building.getTimestamp());
        cret.setEnd(building.getIsEnd());
        cret.setStatus(STATUS_OK);

        return cret;
    }

    @PostMapping("/action")
    public @ResponseBody CommonResponse action(@RequestBody ActionRequest request) {
        if(!doing) {
            ret.setStatus(STATUS_NOTOK);
            return ret;
        }

        try {
            List<CommandWrap> req = request.getCommands();
            for(CommandWrap c : req) {
                boolean res = building.doCommand(c);
                if(!res) throw new Exception();
            }
        } catch(Exception e) {
            ret.setStatus(STATUS_NOTOK);
            return ret;
        }
        building.showStat();

        aret.setElevators(building.getElevatorWrapStatus());
        aret.setEnd(building.getIsEnd());
        aret.setTimestamp(building.getTimestamp());
        aret.setStatus(STATUS_OK);

        return aret;
    }
}
