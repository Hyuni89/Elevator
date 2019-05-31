package com.loren.elevator.connection;

import java.util.List;

import lombok.Data;

@Data
public class CallResponse extends CommonResponse {
    private int timestamp;
    private List<ElevatorWrap> elevators;
    private List<CallWrap> calls;
    private boolean isEnd;
}
