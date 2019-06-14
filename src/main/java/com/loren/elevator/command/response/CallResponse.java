package com.loren.elevator.command.response;

import com.loren.elevator.command.wrapper.CallWrap;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.Data;

@Component
@Data
public class CallResponse extends ActionResponse {
    private List<CallWrap> calls;
}
