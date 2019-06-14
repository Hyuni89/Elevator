package com.loren.elevator;

import com.loren.elevator.connection.ActionRequest;
import com.loren.elevator.connection.ActionResponse;
import com.loren.elevator.connection.CallResponse;
import com.loren.elevator.connection.CommandWrap;
import com.loren.elevator.connection.CommonResponse;
import com.loren.elevator.connection.StartRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
    private CommonResponse commonResponse;

    @Autowired
    private ActionResponse actionResponse;

    @Autowired
    private CallResponse callResponse;

    @Autowired
    private ElevatorService elevatorService;

    public ElevatorController() {
        doing = false;
    }

    @PostMapping("/start")
    public @ResponseBody CommonResponse start(@RequestBody StartRequest request) {
        if(doing) {
            commonResponse.setStatus(STATUS_NOTOK);
            return commonResponse;
        }
        doing = true;

        int buildingHeight = request.getBuildingHeight();
        int elevatorCnt = request.getElevatorCnt();

        commonResponse.setStatus(elevatorService.start(buildingHeight, elevatorCnt) ? STATUS_OK : STATUS_NOTOK);

        return commonResponse;
    }

    @GetMapping("/call")
    public @ResponseBody CommonResponse call() {
        if(!doing) {
            commonResponse.setStatus(STATUS_NOTOK);
            return commonResponse;
        }

        elevatorService.call();

        callResponse.setElevators(elevatorService.getElevatorWrapStatus());
        callResponse.setCalls(elevatorService.getCallWrapStatus());
        callResponse.setTimestamp(elevatorService.getTimestamp());
        callResponse.setEnd(elevatorService.getIsEnd());
        callResponse.setStatus(STATUS_OK);

        return callResponse;
    }

    @PostMapping("/action")
    public @ResponseBody CommonResponse action(@RequestBody ActionRequest request) {
        if(!doing) {
            commonResponse.setStatus(STATUS_NOTOK);
            return commonResponse;
        }

        List<CommandWrap> req = request.getCommands();
        try {
            elevatorService.action(req);
        } catch(Exception e) {
            commonResponse.setStatus(STATUS_NOTOK);
            return commonResponse;
        }

        actionResponse.setElevators(elevatorService.getElevatorWrapStatus());
        actionResponse.setEnd(elevatorService.getIsEnd());
        actionResponse.setTimestamp(elevatorService.getTimestamp());
        actionResponse.setStatus(STATUS_OK);

        if(building.getIsEnd()) {
            System.out.println("===== Congratulation! You've Done It! =====");
            doing = false;
        }

        return actionResponse;
    }
}
