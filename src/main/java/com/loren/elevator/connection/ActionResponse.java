package com.loren.elevator.connection;

import java.util.List;

import lombok.Data;

@Data
public class ActionResponse extends CommonResponse {
    private List<ElevatorWrap> elevators;
    private int timestamp;
    private boolean isEnd;
}
