package com.loren.elevator.command.wrapper;

import java.util.List;

import lombok.Data;

@Data
public class ElevatorWrap {
    private int id;
    private int floor;
    private List<Integer> passengers;
    private String status;
}
