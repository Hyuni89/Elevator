package com.loren.elevator;

import com.loren.elevator.command.ACTION;
import com.loren.elevator.command.CALL;
import com.loren.elevator.command.START;
import com.loren.elevator.command.request.ActionRequest;
import com.loren.elevator.command.request.StartRequest;
import com.loren.elevator.command.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElevatorController {
    @Autowired
    private START start;

    @Autowired
    private CALL call;

    @Autowired
    private ACTION action;

    @PostMapping("/start")
    public @ResponseBody Response start(@RequestBody StartRequest request) {
        return start.invoke(request);
    }

    @GetMapping("/call")
    public @ResponseBody Response call() {
        return call.invoke(null);
    }

    @PostMapping("/action")
    public @ResponseBody Response action(@RequestBody ActionRequest request) {
        return action.invoke(request);
    }
}
