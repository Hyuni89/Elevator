package com.loren.elevator.connection;

import java.util.List;

import lombok.Data;

@Data
public class CommandWrap {
    private int elevatorId;
    private String command;
    private List<Integer> callIds;
}
