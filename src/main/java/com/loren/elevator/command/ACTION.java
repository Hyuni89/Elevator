package com.loren.elevator.command;

import com.loren.elevator.ElevatorService;
import com.loren.elevator.command.request.ActionRequest;
import com.loren.elevator.command.response.ActionResponse;
import com.loren.elevator.command.response.Response;
import com.loren.elevator.command.wrapper.CommandWrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.loren.elevator.command.response.Response.STATUS_NOTOK;
import static com.loren.elevator.command.response.Response.STATUS_OK;

@Component
public class ACTION implements Command<ActionRequest, Response> {
    @Autowired
    private ElevatorService elevatorService;

    @Autowired
    private Response response;

    @Autowired
    private ActionResponse actionResponse;

    @Override
    public Response invoke(ActionRequest request) {
        if(!elevatorService.isRunning()) {
            response.setStatus(STATUS_NOTOK);
            return response;
        }

        List<CommandWrap> req = request.getCommands();
        try {
            elevatorService.action(req);
        } catch(Exception e) {
            response.setStatus(STATUS_NOTOK);
            return response;
        }

        makeResponse();

        return actionResponse;
    }

    private void makeResponse() {
        actionResponse.setElevators(elevatorService.getElevatorWrapStatus());
        actionResponse.setEnd(elevatorService.getIsEnd());
        actionResponse.setTimestamp(elevatorService.getTimestamp());
        actionResponse.setStatus(STATUS_OK);
    }
}
