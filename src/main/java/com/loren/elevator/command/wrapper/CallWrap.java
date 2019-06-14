package com.loren.elevator.command.wrapper;

import lombok.Data;

@Data
public class CallWrap {
    private int id;
    private int timestamp;
    private int start;
    private int end;
}
