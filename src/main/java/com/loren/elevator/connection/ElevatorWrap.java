package com.loren.elevator.connection;

import java.util.List;

import lombok.Data;

@Data
public class ElevatorWrap {
    private int id;
    private int floor;
    private List<Integer> passengers;
    private String status;
}
