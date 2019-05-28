package com.loren.elevator.connection;

import java.util.List;

import lombok.Data;

@Data
public class ActionRequest {
    private List<Command> commands;
}
