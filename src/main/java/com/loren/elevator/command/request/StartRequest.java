package com.loren.elevator.command.request;

import lombok.Data;

@Data
public class StartRequest extends Request {
    private int buildingHeight;
    private int elevatorCnt;
}
