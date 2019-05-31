package com.loren.elevator.connection;

import java.util.List;

import lombok.Data;

@Data
public class CallResponse extends ActionResponse {
    private List<CallWrap> calls;
}
