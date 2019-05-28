package com.loren.elevator.connection;

import lombok.Data;

@Data
public class StartRequest {
    private int buildingHeight;
    private int elevatorCnt;
}
