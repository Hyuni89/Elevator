package com.loren.elevator.connection;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.Data;

@Component
@Data
public class ActionResponse extends CommonResponse {
    private List<ElevatorWrap> elevators;
    private int timestamp;
    private boolean isEnd;
}
