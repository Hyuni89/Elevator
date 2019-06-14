package com.loren.elevator.command;

import com.loren.elevator.command.request.Request;
import com.loren.elevator.command.response.Response;

public interface Command<REQ extends Request, RSP extends Response> {
    RSP invoke(REQ request);
}
