package com.loren.elevator.command;

import com.loren.elevator.ElevatorService;
import com.loren.elevator.command.request.StartRequest;
import com.loren.elevator.command.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.loren.elevator.command.response.Response.STATUS_NOTOK;
import static com.loren.elevator.command.response.Response.STATUS_OK;

@Component
public class START implements Command<StartRequest, Response> {
    @Autowired
    private ElevatorService elevatorService;

    @Autowired
    private Response response;

    @Override
    public Response invoke(StartRequest request) {
        if(elevatorService.isRunning()) {
            response.setStatus(STATUS_NOTOK);
            return response;
        }

        int buildingHeight = request.getBuildingHeight();
        int elevatorCnt = request.getElevatorCnt();

        response.setStatus(elevatorService.start(buildingHeight, elevatorCnt) ? STATUS_OK : STATUS_NOTOK);

        return response;
    }
}
