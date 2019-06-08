package com.loren.elevator.connection;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.Data;

@Component
@Data
public class CallResponse extends ActionResponse {
    private List<CallWrap> calls;
}
