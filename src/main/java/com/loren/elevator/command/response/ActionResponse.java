package com.loren.elevator.command.response;

import com.loren.elevator.command.wrapper.ElevatorWrap;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.Data;

@Component
@Data
public class ActionResponse extends Response {
    private List<ElevatorWrap> elevators;
    private int timestamp;
    private boolean isEnd;
}
