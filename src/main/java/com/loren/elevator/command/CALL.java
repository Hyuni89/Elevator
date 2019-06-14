package com.loren.elevator.command;

import com.loren.elevator.ElevatorService;
import com.loren.elevator.command.request.Request;
import com.loren.elevator.command.response.CallResponse;
import com.loren.elevator.command.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.loren.elevator.command.response.Response.STATUS_NOTOK;
import static com.loren.elevator.command.response.Response.STATUS_OK;

@Component
public class CALL implements Command<Request, Response> {
    @Autowired
    private ElevatorService elevatorService;

    @Autowired
    private Response response;

    @Autowired
    private CallResponse callResponse;

    @Override
    public Response invoke(Request request) {
        if(!elevatorService.isRunning()) {
            response.setStatus(STATUS_NOTOK);
            return response;
        }

        elevatorService.call();
        makeResponse();

        return callResponse;
    }

    private void makeResponse() {
        callResponse.setElevators(elevatorService.getElevatorWrapStatus());
        callResponse.setCalls(elevatorService.getCallWrapStatus());
        callResponse.setTimestamp(elevatorService.getTimestamp());
        callResponse.setEnd(elevatorService.getIsEnd());
        callResponse.setStatus(STATUS_OK);
    }
}
