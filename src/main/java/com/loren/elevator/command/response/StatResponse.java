package com.loren.elevator.command.response;

import com.loren.elevator.command.wrapper.CallWrap;
import com.loren.elevator.command.wrapper.ElevatorWrap;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.Data;

@Component
@Data
public class StatResponse extends Response {
    private List<CallWrap> calls;
    private List<ElevatorWrap> elevators;
}
